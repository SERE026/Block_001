package io.renren.modules.sport.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.renren.common.utils.Result;
import io.renren.modules.sport.constants.SportConstants;
import io.renren.modules.sport.dto.GradeParam;
import io.renren.modules.sport.dto.ProGradeParam;
import io.renren.modules.sport.dto.ProjectGradeDTO;
import io.renren.modules.sport.dto.StudentDTO;
import io.renren.modules.sport.entity.*;
import io.renren.modules.sport.service.*;
import io.renren.modules.sport.util.AgeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service("gradeService")
public class GradeServiceImpl implements GradeService {

    @Autowired
    private StudentGradeService studentGradeService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ScoreSuggestionService suggestionService;
    @Autowired
    private ProjectConfigService projectConfigService;
    @Autowired
    private ProjectGradeService projectGradeService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private BmiGradeService bmiGradeService;
    @Autowired
    private TrainGoalService trainGoalService;
    @Autowired
    private BmiConfigService bmiConfigService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveGrade(GradeParam grade) {

        Result checkResult = checkParam(grade);
        if(!checkResult.isOk()){
            return checkResult;
        }
        Student stu = studentService.getById(grade.getStudentId());
        stu.setGradeFlag(1);
        studentService.updateById(stu);
        Integer age = AgeUtils.getAgeByBirthday(stu.getBirthday());
        LocalDateTime checkTime = LocalDateTime.parse(grade.getCheckTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        StudentGrade studentGrade = StudentGrade.builder()
                .studentId(grade.getStudentId())
                .createTime(LocalDateTime.now())
                .checkTime(checkTime)
                .height(grade.getHeight())
                .weight(grade.getWeight())
                .trainHours(grade.getTrainHours())
                .attendance(grade.getAttendance())
                .age(age)
                .teacherName(grade.getTeacherName())
                .build();
        //先保存
        studentGradeService.save(studentGrade);
        BigDecimal hh = grade.getHeight().multiply(grade.getHeight());
        BmiConfig bmiConfig = getBmiConfig(age,stu.getGender());
        BmiGrade bmiGrade = BmiGrade.builder()
                .bmiConfigId(bmiConfig.getId())
                .checkTime(checkTime)
                .height(grade.getHeight())
                .weight(grade.getWeight())
                .bmiGrade(grade.getWeight().divide(hh,10,BigDecimal.ROUND_HALF_UP)
                        .multiply(new BigDecimal("10000")).setScale(2,BigDecimal.ROUND_HALF_UP))
                .studentId(grade.getStudentId())
                .teacherName(grade.getTeacherName())
                .createTime(LocalDateTime.now())
                .age(age)
                .build();
        bmiGrade.setAgeRange(bmiConfig.getMinAge()+"-"+bmiConfig.getMaxAge());
        bmiGrade.setScore(bmiGrade.getBmiGrade());
        bmiGrade.setStuGradeId(studentGrade.getId());
        bmiGradeService.save(bmiGrade);

        List<ProjectGrade> gradeList = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(grade.getProList())){
            List<ScoreSuggestion> suggestionList = suggestionService.list();
            grade.getProList().forEach(g ->{
                //获取评分等级项
                ProjectConfig pcconf = getProjectConfigByAgeWithGradeRange(stu, age, g);
                String optSug = getScoreSuggestion(suggestionList, pcconf);
                ProjectGrade pg = ProjectGrade.builder()
                        .projectGrade(g.getProGrade())
                        .projectId(g.getProjectId())
                        .studentId(grade.getStudentId())
                        .score(pcconf.getScoreLevel())
                        .projectConfigId(pcconf.getId())
                        .suggestion(optSug)
                        .age(age)
                        .checkTime(checkTime)
                        .teacherName(grade.getTeacherName())
                        .createTime(LocalDateTime.now())
                        .build();
                pg.setAgeRange(pcconf.getMinAge()+"-"+pcconf.getMaxAge());
                gradeList.add(pg);
            });

            Map<String, BigDecimal> gradeMap = getGradeMapGroupByProjectCode(gradeList);
            try {
                BeanUtils.populate(studentGrade,gradeMap);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            //测评结果判断
            //        /*身体素质测试总分÷项目数≤2					差
            //        身体素质测试总分÷项目数=2~3					较差
            //        身体素质测试总分÷项目数=3~4					一般
            //        身体素质测试总分÷项目数=4~5					良好
            //        身体素质测试总分÷项目数=5					优秀
            //        */
            combineScoreSuggestion(suggestionList, gradeList, studentGrade);
            studentGrade.setAgeRange(bmiGrade.getAgeRange());

            studentGradeService.updateById(studentGrade);
            bmiGradeService.updateById(bmiGrade);
            gradeList.forEach(e ->e.setStuGradeId(studentGrade.getId()));
            projectGradeService.saveBatch(gradeList);
        }
        return Result.ok();
    }

    /**
     * 根据年龄性别 获取BMI基准
     * @param age
     * @param gender
     * @return
     */
    private BmiConfig getBmiConfig(Integer age,Integer gender) {
        List<BmiConfig> bmiConfigList = bmiConfigService.list();
        return bmiConfigList.stream()
                .filter(tg -> tg.getMinAge() <=age && tg.getMaxAge()>=age && tg.getGender().equals(gender))
                .findFirst().get();

    }

    //综合评分
    private void combineScoreSuggestion(List<ScoreSuggestion> suggestionList, List<ProjectGrade> gradeList, StudentGrade studentGrade) {
        Double avg = gradeList.stream().mapToDouble(projectGrade -> projectGrade.getScore().doubleValue()).average().getAsDouble();
        log.info("学员：{} 综合评分值：{}",studentGrade.getStudentId(),avg);
        studentGrade.setScore(new BigDecimal(avg));
        Integer passStatus = studentGrade.getScore().compareTo(new BigDecimal("3")) < 0 ? 0 : 1;
        studentGrade.setStatus(passStatus);

        String sug = "满分";
        for(ScoreSuggestion s : suggestionList){
            if(!"all".equals(s.getProjectCode())){
                continue;
            }
            if(s.getMaxScore().compareTo(new BigDecimal(avg)) >= 0
                    && s.getMinScore().compareTo(new BigDecimal(avg)) <= 0){
                sug = s.getSuggestion();
                break;
            }
        }
        studentGrade.setSuggestion(sug);
    }

    //将成绩转换成Map<key/*属性*/,val/*成绩*/>
    private Map<String, BigDecimal> getGradeMapGroupByProjectCode(List<ProjectGrade> gradeList) {
        Map<Integer, ProjectGrade> gradeMapGroupById = gradeList.stream().collect(Collectors.toMap(ProjectGrade::getProjectId, Function.identity()));
        List<Project> projectList = projectService.list();
        Map<String, BigDecimal> gradeMap = new HashMap<>();
        projectList.forEach(p -> {
            ProjectGrade pg = gradeMapGroupById.get(p.getId());
            if (Objects.isNull(pg)) {
                return;
            }
            gradeMap.put(underlineToCamel(p.getProjectCode()), pg.getProjectGrade());
        });
        return gradeMap;
    }

    /*获取单项评分建议*/
    private String getScoreSuggestion(List<ScoreSuggestion> suggestionList, ProjectConfig pcconf) {
        Optional<ScoreSuggestion> optSug = suggestionList.stream().filter(s -> {
            return s.getMaxScore().compareTo(pcconf.getScoreLevel()) >= 0 && s.getMinScore().compareTo(pcconf.getScoreLevel()) <= 0;
        }).findFirst();
        return optSug.isPresent() ? optSug.get().getSuggestion() : "满分";
    }

    /** 根据年龄,性别,成绩 获取 基准信息*/
    private ProjectConfig getProjectConfigByAgeWithGradeRange(Student stu, Integer age, ProGradeParam proGrade) {
        ProjectConfig projectConfig = projectConfigService.getByAgeWithGradeRange(proGrade.getProjectId(),proGrade.getProGrade(),age,stu.getGender());
        if(projectConfig == null){
            Project project = projectService.getById(proGrade.getProjectId());
            log.error("学员ID:{},gender:{},age:{},proId:{},proGrade:{}",stu.getId(),stu.getGender(),age,proGrade.getProjectId(),proGrade.getProGrade());
            throw new RuntimeException(project.getProjectName()+",在年龄:"+age+" 阶段配置有误");
        }
        return projectConfig;
    }

    private Result checkParam(GradeParam grade) {
        if(Objects.isNull(grade.getCheckTime())){
            return Result.error("检测时间不能为空");
        }
        if(Objects.isNull(grade.getHeight()) || Objects.isNull(grade.getWeight())){
            return Result.error("身高或者体重不能为空");
        }
        return Result.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateGrade(GradeParam grade) {
        Student stu = studentService.getById(grade.getStudentId());
        Integer age = AgeUtils.getAgeByBirthday(stu.getBirthday());

        //日期为空,从数据库获取
        StudentGrade lastStuGrade = studentGradeService.getLastGrade(stu.getId());
        LocalDateTime checkTime = lastStuGrade.getCheckTime();
        if(Objects.nonNull(grade.getCheckTime())){
            checkTime = LocalDateTime.parse(grade.getCheckTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        //查询
        BmiGrade oldbmiGrade = bmiGradeService.getLastByStuGradeId(lastStuGrade.getId());
        BigDecimal hh = grade.getHeight().multiply(grade.getHeight());
        BmiConfig bmiConfig = getBmiConfig(age,stu.getGender());
        BmiGrade bmiGrade = BmiGrade.builder()
                .id(oldbmiGrade.getId())
                .checkTime(checkTime)
                .height(grade.getHeight())
                .weight(grade.getWeight())
                .bmiGrade(grade.getWeight().divide(hh,10,BigDecimal.ROUND_HALF_UP)
                        .multiply(new BigDecimal("10000")).setScale(2,BigDecimal.ROUND_HALF_UP))
                .studentId(grade.getStudentId())
                .teacherName(grade.getTeacherName())
                .build();
        bmiGrade.setAgeRange(bmiConfig.getMinAge() + "-"+bmiConfig.getMaxAge());
        bmiGrade.setBmiConfigId(bmiConfig.getId());
        bmiGrade.setScore(bmiGrade.getBmiGrade());
        List<ProjectGrade> gradeList = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(grade.getProList())){
            List<ScoreSuggestion> suggestionList = suggestionService.list();
            LocalDateTime finalCheckTime = checkTime;
            grade.getProList().forEach(g ->{
                //获取评分等级项
                ProjectConfig pcconf = getProjectConfigByAgeWithGradeRange(stu, age, g);
                String optSug = getScoreSuggestion(suggestionList, pcconf);
                //查询
                ProjectGrade oldPg = projectGradeService.getByStuGradeIdWithProjectId(lastStuGrade.getId(),g.getProjectId());
                ProjectGrade pg = ProjectGrade.builder()
                        .id(oldPg !=null ? oldPg.getId() : null)
                        .projectGrade(g.getProGrade())
                        .projectId(g.getProjectId())
                        .studentId(grade.getStudentId())
                        .projectConfigId(pcconf.getId())
                        .score(pcconf.getScoreLevel())
                        .suggestion(optSug)
                        .age(age)
                        .ageRange(pcconf.getMinAge()+"-"+pcconf.getMaxAge())
                        .checkTime(finalCheckTime)
                        .teacherName(grade.getTeacherName())
                        .build();
                if(oldPg == null){
                    pg.setCreateTime(LocalDateTime.now());
                }
                gradeList.add(pg);
            });
            Map<String, BigDecimal> gradeMap = getGradeMapGroupByProjectCode(gradeList);
            //查询TODO
            StudentGrade studentGrade = StudentGrade.builder()
                    .id(lastStuGrade.getId())
                    .studentId(grade.getStudentId())
                    .checkTime(checkTime)
                    .height(grade.getHeight())
                    .weight(grade.getWeight())
                    .trainHours(grade.getTrainHours())
                    .attendance(grade.getAttendance())
                    .ageRange(bmiGrade.getAgeRange())
                    .teacherName(grade.getTeacherName())
                    .build();
            //
            try {
                BeanUtils.populate(studentGrade,gradeMap);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            //测评结果判断
            //        /*身体素质测试总分÷项目数≤2					差
            //        身体素质测试总分÷项目数=2~3					较差
            //        身体素质测试总分÷项目数=3~4					一般
            //        身体素质测试总分÷项目数=4~5					良好
            //        身体素质测试总分÷项目数=5					优秀
            //        */
            combineScoreSuggestion(suggestionList, gradeList, studentGrade);

            studentGradeService.updateById(studentGrade);
            bmiGradeService.updateById(bmiGrade);
            gradeList.forEach(e ->{
                e.setStuGradeId(studentGrade.getId());
                projectGradeService.saveOrUpdate(e);
            });
        }
        return Result.ok();
    }

    @Override
    public Result getLastGrade(Integer studentId) {
        List<StudentGrade> stuGradeList = studentGradeService.getLastTwoGrade(studentId);
        StudentGrade lastStuGrade = StudentGrade.builder().build();
        if(stuGradeList.size() == 1){
            lastStuGrade = stuGradeList.get(0);
        }else {
            lastStuGrade = stuGradeList.stream().max(Comparator.comparing(StudentGrade::getId)).get();
        }
        List<Integer> gradeIds = Lists.newArrayList();
        gradeIds.add(lastStuGrade.getId());
        List<ProjectGradeDTO> lastProGradeList = projectGradeService.getInGradeIds(gradeIds);


        BmiGrade bmiGrade = bmiGradeService.getLastByStuGradeId(lastStuGrade.getId());
        return Result.ok().put("lastProGradeList",lastProGradeList)
                .put("bmiGrade",bmiGrade)
                .put("lastStuGrade",lastStuGrade);
    }

    /**
     * 下划线 转 驼峰
     * @param param
     * @return
     */
    private String underlineToCamel(String param) {

        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = Character.toLowerCase(param.charAt(i));
            if (c == '_'){
                if (++i<len){
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static double applyGradeAsDouble(ProjectGradeDTO projectGrade) {
        BigDecimal score = /*ProjectGrade::getScore;*/projectGrade.getScore();
        return score.doubleValue();
    }

    @Override
    public Result queryDetail(Integer studentId) {
        //基本信息
        StudentDTO stu = studentService.getStudentWithSchoolNameById(studentId);

        Integer age = AgeUtils.getAgeByBirthday(stu.getBirthday());
        stu.setAge(age);
        //训练目标
        List<TrainGoal> trainGoalList = trainGoalService.queryAll();
        TrainGoal trainGoal = trainGoalList.stream().filter(tg -> tg.getMinAge() <=age && tg.getMaxAge()>=age).findFirst().get();
        //BMI测试值
        List<BmiGrade> bmiGradeList = bmiGradeService.getByStudentId(studentId);
        BmiGrade lastBmiGrade = bmiGradeList.stream().max(Comparator.comparing(BmiGrade::getId)).get();

        //最近两次身体素质测评数据
        List<StudentGrade> stuGradeList = studentGradeService.getLastTwoGrade(studentId);
        StudentGrade lastStuGrade = StudentGrade.builder().build();
        StudentGrade prevStuGrade = StudentGrade.builder().build();
        if(stuGradeList.size() == 1){
            lastStuGrade = stuGradeList.get(0);
        }else {
            lastStuGrade = stuGradeList.stream().max(Comparator.comparing(StudentGrade::getId)).get();
            prevStuGrade = stuGradeList.stream().min(Comparator.comparing(StudentGrade::getId)).get();
        }
        //List<Integer> gradeIds = stuGradeList.stream().map(StudentGrade::getId).collect(Collectors.toList());
        List<Integer> gradeIds = Lists.newArrayList();
        gradeIds.add(lastStuGrade.getId());
        List<ProjectGradeDTO> lastProGradeList = projectGradeService.getInGradeIds(gradeIds);
        Map<Integer,ProjectGradeDTO> lastProGradeMap = lastProGradeList.stream().collect(Collectors.toMap(ProjectGradeDTO::getProjectId,Function.identity(),(o1,o2)->o1));
        //满分配置信息
        //List<Integer> projectIds = lastProGradeList.stream().map(ProjectGradeDTO::getProjectId).collect(Collectors.toList());
        List<Integer> projectIds = lastProGradeMap.keySet().stream().collect(Collectors.toList());
        List<ProjectConfig> projectList = projectConfigService.getByFullScoreByProjectIds(projectIds,age,stu.getGender());
        Map<Integer,ProjectConfig> proFullScore = Maps.newHashMap();
        for (ProjectConfig pc : projectList) {
            ProjectGradeDTO dto = lastProGradeMap.get(pc.getProjectId());
            if(Objects.nonNull(dto)){
                proFullScore.put(pc.getProjectId(),pc);
            }
        }

        //上次项目成绩信息
        gradeIds.clear();
        gradeIds.add(prevStuGrade.getId());
        List<ProjectGradeDTO> prevProGradeList = projectGradeService.getInGradeIds(gradeIds);
//        Map prevProGradeMap = Maps.newHashMap();
//        if(!CollectionUtils.isEmpty(prevProGradeList)){
//            prevProGradeMap = prevProGradeList.stream().collect(Collectors.toMap(ProjectGradeDTO::getProjectId,Function.identity(),(o1,o2)->o1));
//        }

        //计算最近两次的平均分
         /*身体素质测试总分÷项目数≤2					差
        身体素质测试总分÷项目数=2~3					较差
        身体素质测试总分÷项目数=3~4					一般
        身体素质测试总分÷项目数=4~5					良好
        身体素质测试总分÷项目数=5					优秀
        */
//        Map<Integer, Double> averageMap = lastProGradeList.stream().collect(Collectors.groupingBy(ProjectGradeDTO::getStuGradeId,
//                Collectors.averagingDouble(GradeServiceImpl::applyGradeAsDouble)));

        //测评结果判断
//        Optional<Integer> averageKey = averageMap.keySet().stream().max(Comparator.naturalOrder());
//        Double score = averageKey.isPresent() ? averageMap.get(averageKey.get()) : 0.0D;
        String passDesc = lastStuGrade.getStatus() == 1 ? "通过" : "不通过";
        String scoreDesc = getString(lastStuGrade.getScore().doubleValue());

        //Radar chart
        List<Map<String, Object>> radarChart = radarChart(lastProGradeList);
        //BMI chart
        Map bmiChart = bmiChart(lastBmiGrade);
        //Bar chart
        List tgmd3Chart = tgmdChart(lastProGradeList,prevProGradeList,proFullScore);


        return Result.ok()
                .put("student",stu)
                .put("trainGoal",trainGoal)
                .put("lastStuGrade",lastStuGrade)  //最近一次成绩
                .put("lastBmiGrade",lastBmiGrade)  //最近一次
                .put("proFullScore",proFullScore)  //满配
                .put("lastProGradeList",lastProGradeList)  //最近一次项目成绩
                .put("prevProGradeList",prevProGradeList)
                .put("passDesc",passDesc)
                .put("scoreDesc",scoreDesc)
                .put("radarChart",radarChart)
                .put("tgmd3Chart",tgmd3Chart)
                .put("bmiChart",bmiChart)
                ;
    }

    private Map bmiChart(BmiGrade lastBmiGrade) {
        BmiConfig bmiConfig = bmiConfigService.getById(lastBmiGrade.getBmiConfigId());
        Map bmiChart = Maps.newHashMap();
        // 判断lastBmiGrade 处于那个范围
        bmiChart.put("bmiConf",bmiConfig);
        bmiChart.put("lastBmiGrade",lastBmiGrade);
        return bmiChart;
    }

    private List<Map<String, Object>> radarChart(List<ProjectGradeDTO> lastProGradeList) {
        List<Map<String,Object>> radarChart = Lists.newArrayList();
        lastProGradeList.forEach(pg -> {
            Map map = Maps.newHashMap();
            map.put("projectType",pg.getProjectType());
            map.put("score",pg.getScore());
            radarChart.add(map);
        });
        return radarChart;
    }

    private String getString(Double score) {
        String scoreDesc = "";
        if(score <= 2){
            scoreDesc = "差";
        }else if(score <=3){
            scoreDesc = "较差";
        }else if(score <=4){
            scoreDesc = "一般";
        }else if(score <=5){
            scoreDesc = "良好";
        }else if(score > 5){
            scoreDesc = "优秀";
        }
        return scoreDesc;
    }

    private List<Object[]> tgmdChart(List<ProjectGradeDTO> lastProGradeList,List<ProjectGradeDTO> prevProGradeList,Map<Integer,ProjectConfig> fullScoreMap) {
        List tgmd3Chart = Lists.newArrayList();

        //[3] 三维数组
                /*['product', '满分', '测试'],
				['2015', 43.3, 85.8],
				['2016', 83.1, 73.4],
				['2017', 86.4, 65.2],
				['2018', 72.4, 53.9]*/

        Object[] header = new Object[]{"product", "测试","满分"};
        tgmd3Chart.add(header);
        Object[] lastProGrade = new Object[3];
        lastProGrade[0] = lastProGradeList.get(0).getCheckTime().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        lastProGrade[1] = BigDecimal.ZERO;
        Integer projectId = 0;
        for (ProjectGradeDTO pg : lastProGradeList){
            if("tgmd3_check".equals(pg.getProjectCode())){
                lastProGrade[1] = pg.getProjectGrade();
                projectId = pg.getProjectId();
                break;
            }
        }
        ProjectConfig config = fullScoreMap.get(projectId);
        BigDecimal fullScore = Objects.isNull(config) ? new BigDecimal("96") : config.getMinScore();
        BigDecimal checkScore = (BigDecimal) lastProGrade[1];
        lastProGrade[2] = fullScore.subtract(checkScore).setScale(2,BigDecimal.ROUND_HALF_UP);
        tgmd3Chart.add(lastProGrade);

        if(!CollectionUtils.isEmpty(prevProGradeList)){
            Object[] prevProGrade = new Object[3];
            prevProGrade[0] = prevProGradeList.get(0).getCheckTime().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            for (ProjectGradeDTO pg : prevProGradeList){
                if("tgmd3_check".equals(pg.getProjectCode())){
                    prevProGrade[1] = pg.getProjectGrade();
                    break;
                }
            }
            BigDecimal checkPrevScore = (BigDecimal) prevProGrade[1];
            prevProGrade[2] = fullScore.subtract(checkPrevScore).setScale(2,BigDecimal.ROUND_HALF_UP);
            tgmd3Chart.add(prevProGrade);
        }
        //最近一次纵坐标
        return tgmd3Chart;
    }
}
