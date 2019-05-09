package io.renren.modules.sport.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.renren.common.utils.Result;
import io.renren.modules.sport.dto.GradeParam;
import io.renren.modules.sport.dto.ProjectGradeDTO;
import io.renren.modules.sport.dto.StudentDTO;
import io.renren.modules.sport.entity.*;
import io.renren.modules.sport.service.*;
import io.renren.modules.sport.util.AgeUtils;
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
        //todo
        List<ProjectConfig> projectConfigList = projectConfigService.list();
        List<ScoreSuggestion> suggestionList = suggestionService.list();
        Student stu = studentService.getById(grade.getStudentId());
        Integer age = AgeUtils.getAgeByBirthday(stu.getBirthday());
        LocalDateTime checkTime = LocalDateTime.parse(grade.getCheckTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        BigDecimal hh = grade.getHeight().multiply(grade.getHeight());
        BmiGrade bmiGrade = BmiGrade.builder()
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
        bmiGrade.setScore(bmiGrade.getBmiGrade());

        List<ProjectGrade> gradeList = Lists.newArrayList();

        if(!CollectionUtils.isEmpty(grade.getProList())){
            grade.getProList().forEach(g ->{
                ProjectConfig pcconf = projectConfigList.stream().filter(pc ->{
                    return (age>=pc.getMinAge() && age<=pc.getMaxAge() && g.getProGrade().compareTo(pc.getMinScore())>=0
                            && g.getProGrade().compareTo(pc.getMaxScore())<=0);
                }).findFirst().get();

                Optional<ScoreSuggestion> optSug = suggestionList.stream().filter(s -> {
                    return s.getMaxScore().compareTo(pcconf.getScoreLevel())>=0 && s.getMinScore().compareTo(pcconf.getScoreLevel())<=0;
                }).findFirst();
                ProjectGrade pg = ProjectGrade.builder()
                        .projectGrade(g.getProGrade())
                        .projectId(g.getProjectId())
                        .studentId(grade.getStudentId())
                        .score(pcconf.getScoreLevel())
                        .suggestion(optSug.isPresent() ? optSug.get().getSuggestion() : "满分")
                        .age(age)
                        .ageRange(pcconf.getMinAge()+"-"+pcconf.getMaxAge())
                        .checkTime(checkTime)
                        .teacherName(grade.getTeacherName())
                        .createTime(LocalDateTime.now())
                        .build();
                gradeList.add(pg);
                bmiGrade.setAgeRange(pg.getAgeRange());
            });
            Map<Integer,ProjectGrade> gradeMapGroupById = gradeList.stream().collect(Collectors.toMap(ProjectGrade::getProjectId, Function.identity()));
            List<Project> projectList = projectService.list();
            Map<String, BigDecimal> gradeMap = new HashMap<>();
            projectList.forEach(p -> {
                ProjectGrade pg = gradeMapGroupById.get(p.getId());
                if(Objects.isNull(pg)){
                    return;
                }

                gradeMap.put(underlineToCamel(p.getProjectCode()),pg.getProjectGrade());
            });
            StudentGrade studentGrade = StudentGrade.builder()
                    .studentId(grade.getStudentId())
                    .createTime(LocalDateTime.now())
                    .checkTime(checkTime)
                    .height(grade.getHeight())
                    .weight(grade.getWeight())
                    .trainHours(grade.getTrainHours())
                    .attendance(grade.getAttendance())
                    .age(age)
                    .ageRange(bmiGrade.getAgeRange())
                    .teacherName(grade.getTeacherName())
                    .build();
            //
            try {
                BeanUtils.populate(studentGrade,gradeMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //测评结果判断
            //        /*身体素质测试总分÷项目数≤2					差
            //        身体素质测试总分÷项目数=2~3					较差
            //        身体素质测试总分÷项目数=3~4					一般
            //        身体素质测试总分÷项目数=4~5					良好
            //        身体素质测试总分÷项目数=5					优秀
            //        */
            Double avg = gradeList.stream().mapToDouble(projectGrade -> projectGrade.getScore().doubleValue()).average().getAsDouble();
            studentGrade.setScore(new BigDecimal(avg));

            ProjectConfig pcconf = projectConfigList.stream().filter(pc ->{
                return (age>=pc.getMinAge() && age<=pc.getMaxAge() && studentGrade.getScore().compareTo(pc.getMinScore())>=0
                        && studentGrade.getScore().compareTo(pc.getMaxScore())<=0);
            }).findFirst().get();
            Optional<ScoreSuggestion> optSug = suggestionList.stream().filter(s -> {
                return s.getMaxScore().compareTo(pcconf.getScoreLevel())>=0
                        && s.getMinScore().compareTo(pcconf.getScoreLevel())<=0
                        && "all".equals(s.getProjectCode());
            }).findFirst();
            studentGrade.setSuggestion(optSug.isPresent() ? optSug.get().getSuggestion(): "满分");
            studentGradeService.save(studentGrade);
            bmiGrade.setStuGradeId(studentGrade.getId());
            bmiGradeService.save(bmiGrade);
            gradeList.forEach(e ->e.setStuGradeId(studentGrade.getId()));
            projectGradeService.saveBatch(gradeList);
        }
        return Result.ok();
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
        //BMI config
        List<BmiConfig> bmiConfigList = bmiConfigService.list();
        List<BmiConfig> bmiConfigs = bmiConfigList.stream()
                .filter(tg -> tg.getMinAge() <=age && tg.getMaxAge()>=age && tg.getGender().equals(stu.getGender()))
                .collect(Collectors.toList());

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
        List<ProjectConfig> projectList = projectConfigService.getByFullScoreByProjectIds(projectIds);
        Map<Integer,ProjectConfig> proFullScore = Maps.newHashMap();
        for (ProjectConfig pc : projectList) {
            if(pc.getMinAge() <=age && pc.getMaxAge()>=age && pc.getGender().equals(stu.getGender())){
                ProjectGradeDTO dto = lastProGradeMap.get(pc.getProjectId());
                if(Objects.nonNull(dto)){
                    proFullScore.put(pc.getProjectId(),pc);
                }
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
        Map<Integer, Double> averageMap = lastProGradeList.stream().collect(Collectors.groupingBy(ProjectGradeDTO::getStuGradeId,
                Collectors.averagingDouble(GradeServiceImpl::applyGradeAsDouble)));

        //测评结果判断
        Optional<Integer> averageKey = averageMap.keySet().stream().max(Comparator.naturalOrder());
        Double score = averageKey.isPresent() ? averageMap.get(averageKey.get()) : 0.0D;
        String passDesc = score < 3 ? "不通过" : "通过";
        String scoreDesc = getString(score);

        //Radar chart
        List<Map<String, Object>> radarChart = radarChart(lastProGradeList);
        //BMI chart
        Map bmiChart = bmiChart(lastBmiGrade,bmiConfigs);
        //Bar chart
        Map tgmd3Chart = tgmdChart(lastProGradeList,prevProGradeList,proFullScore);


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

    private Map bmiChart(BmiGrade lastBmiGrade, List<BmiConfig> bmiConfigs) {
        Map bmiChart = Maps.newHashMap();
        // 判断lastBmiGrade 处于那个范围
        bmiChart.put("bmiConf",bmiConfigs.get(0));
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

    private Map tgmdChart(List<ProjectGradeDTO> lastProGradeList,List<ProjectGradeDTO> prevProGradeList,Map<Integer,ProjectConfig> fullScoreMap) {
        Map tgmd3Chart = Maps.newHashMap();
        //横坐标
        List tgmd3DataX = Lists.newArrayList();
        tgmd3DataX.add(lastProGradeList.get(0).getCheckTime().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
        if(!CollectionUtils.isEmpty(prevProGradeList)){
            tgmd3DataX.add(prevProGradeList.get(0).getCheckTime().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
        }
        tgmd3Chart.put("tgmd3DataX",tgmd3DataX);

        List tgmd3DataScore = Lists.newArrayList();

        //最近一次纵坐标
        Integer projectId = 0;
        for (ProjectGradeDTO pg : lastProGradeList){
            if("tgmd3_check".equals(pg.getProjectCode())){
                tgmd3DataScore.add(pg.getProjectGrade());
                projectId = pg.getProjectId();
                break;
            }
        }
        if(!CollectionUtils.isEmpty(prevProGradeList)){
            for (ProjectGradeDTO pg : prevProGradeList){
                if("tgmd3_check".equals(pg.getProjectCode())){
                    tgmd3DataScore.add(pg.getProjectGrade());
                    projectId = pg.getProjectId();
                    break;
                }
            }
        }
        tgmd3Chart.put("tgmd3ScoreY",tgmd3DataScore);
        ProjectConfig config = fullScoreMap.get(projectId);
        BigDecimal fullScore = Objects.isNull(config) ? new BigDecimal("100") : config.getMinScore();
        //上次纵坐标
        List fullTgmd3Score = Lists.newArrayList();
        while(fullTgmd3Score.size() < tgmd3DataScore.size()){
            fullTgmd3Score.add(fullScore);
        }
        tgmd3Chart.put("fullTgmd3ScoreY",fullTgmd3Score);

        return tgmd3Chart;
    }
}
