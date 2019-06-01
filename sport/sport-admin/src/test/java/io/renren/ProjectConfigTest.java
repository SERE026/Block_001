package io.renren;

import com.alibaba.fastjson.JSON;
import io.renren.modules.sport.dao.AreaMapper;
import io.renren.modules.sport.dao.ProjectConfigMapper;
import io.renren.modules.sport.entity.Area;
import io.renren.modules.sport.entity.ProjectConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectConfigTest {

    @Autowired
    private ProjectConfigMapper confMapper;

    @Test
    public void test(){
        confMapper.selectById(1);
    }

    @Test
    @Transactional
    public void contextLoads() throws  Exception {
        add305();
        add608();
        add911();


        System.out.println("---------");
    }

    private void add911() {
        List<Map> list1 = confMapper.importSheet3();
        int age = 9;
        list1.forEach(e->{
            System.out.println(JSON.toJSONString(e));
            ProjectConfig pc = ProjectConfig.builder()
                    .projectId((Integer) e.get("project_id"))
                    .projectType((String) e.get("project_type"))
                    .projectName((String)e.get("project_name"))
                    .createTime(LocalDateTime.now())
                    .build();
            ;
            addProConf(e,pc,age,1);
            addProConf(e,pc,age,2);
            addProConf(e,pc,age+1,1);
            addProConf(e,pc,age+1,2);
            addProConf(e,pc,age+2,1);
            addProConf(e,pc,age+2,2);
        });
    }
    private void add608() {
        List<Map> list1 = confMapper.importSheet2();
        int age = 6;
        list1.forEach(e->{
            System.out.println(JSON.toJSONString(e));
            ProjectConfig pc = ProjectConfig.builder()
                    .projectId((Integer) e.get("project_id"))
                    .projectType((String) e.get("project_type"))
                    .projectName((String)e.get("project_name"))
                    .createTime(LocalDateTime.now())
                    .build();
            ;
            addProConf(e,pc,age,1);
            addProConf(e,pc,age,2);
            addProConf(e,pc,age+1,1);
            addProConf(e,pc,age+1,2);
            addProConf(e,pc,age+2,1);
            addProConf(e,pc,age+2,2);
        });
    }

    private void add305(){
        List<Map> list1 = confMapper.importSheet1();
        int age = 3;
        list1.forEach(e->{
            System.out.println(JSON.toJSONString(e));
            ProjectConfig pc = ProjectConfig.builder()
                    .projectId((Integer) e.get("project_id"))
                    .projectType((String) e.get("project_type"))
                    .projectName((String)e.get("project_name"))
                    .createTime(LocalDateTime.now())
                    .build();
            ;
            addProConf(e,pc,age,1);
            addProConf(e,pc,age,2);
            addProConf(e,pc,age+1,1);
            addProConf(e,pc,age+1,2);
            addProConf(e,pc,age+2,1);
            addProConf(e,pc,age+2,2);
        });
    }

    private void addProConf(Map map,ProjectConfig pc,int age, int gender) {
        String a1 = (String) map.get("age_"+age+""+gender);
        if(a1.startsWith("＞")){
            pc.setMinScore(new BigDecimal(a1.replace("＞","")));
            pc.setMaxScore(new BigDecimal("1000"));
        }else if(a1.startsWith("＜")){
            pc.setMaxScore(new BigDecimal(a1.replace("＜","")));
            pc.setMinScore(BigDecimal.ZERO);
        }else{
            BigDecimal[] s = score(a1);
            pc.setMinScore(s[0]);
            pc.setMaxScore(s[1]);
        }
        pc.setMinAge(age);
        pc.setMaxAge(age);
        pc.setGender(gender);
        pc.setRemark(a1);
        String scoreL = (String) map.get("score");
        pc.setScoreLevel(new BigDecimal(scoreL.substring(0,1)));

        confMapper.insert(pc);
        pc.setId(null);
    }

    public static BigDecimal[] score(String str){
        int[] idx = appearNumber(str,"-");
        String min,max;
        if(idx[0] == 0){
            // "-49.8--32.5";
            //"-49.8-32.5";
            min = str.substring(0,idx[1]);
            max = str.substring(idx[1]+1,str.length());
        }else {
            //"49.8-32.5";
            //"49.8--32.5";
            min = str.substring(0,idx[0]);
            max = str.substring(idx[0]+1,str.length());
        }
        System.out.println("min="+min+"--->max="+max);
        BigDecimal maxS = new BigDecimal(max);
        BigDecimal minS = new BigDecimal(min);
        if(minS.compareTo(maxS)>0){
            return new BigDecimal[]{maxS,minS};
        }
        return new BigDecimal[]{minS,maxS};
    }
    public static int[] appearNumber(String srcText, String findText) {
        int idx[] = new int[3];
        int count = 0;
        int index = 0;
        while ((index = srcText.indexOf(findText, index)) != -1) {
            System.out.println("index="+index);
            idx[count] = index;
            index = index + findText.length();
            count++;
        }
        System.out.println("----count="+count);
        return idx;
    }

}


