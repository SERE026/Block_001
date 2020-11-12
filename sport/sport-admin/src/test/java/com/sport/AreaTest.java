package com.sport;

import com.alibaba.fastjson.JSON;
import com.sport.modules.sport.dao.AreaMapper;
import com.sport.modules.sport.entity.Area;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaTest {

    @Autowired
    private AreaMapper areaMapper;

    @Test
    public void contextLoads() throws  Exception {
        StringBuilder json = new StringBuilder();
        File file = new File("C:\\Users\\secoo\\Desktop\\软件素材\\area.json");

        InputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String str = "";
        while (StringUtils.isNotEmpty((str = bufferedReader.readLine()))){
           json.append(str);
        }
        System.out.println(json.toString());
        List<CityDTO> list = JSON.parseArray(json.toString(), CityDTO.class);
        list.forEach(p ->{
            Area province = Area.builder()
                    .parentId(0)
                    .areaCode(p.getCode())
                    .areaName(p.getName())
                    .parentCode("000000")
                    .level(1)
                    .build();
            areaMapper.insert(province);
            p.cityList.forEach(c ->{
                Area city = Area.builder()
                        .parentId(province.getId())
                        .areaCode(c.getCode())
                        .areaName(c.getName())
                        .parentCode(province.getAreaCode())
                        .level(2)
                        .build();
                areaMapper.insert(city);

                c.areaList.forEach(a ->{
                    Area area = Area.builder()
                            .parentId(city.getId())
                            .areaCode(a.getCode())
                            .areaName(a.getName())
                            .parentCode(city.getAreaCode())
                            .level(3)
                            .build();
                    areaMapper.insert(area);
                });
            });
        });

        System.out.println(JSON.toJSON(list));
        System.out.println("---------");
    }

}
@Data
@NoArgsConstructor
@AllArgsConstructor
class CityDTO {
    String code;
    String name;
    List<AreaDTO> cityList;

}
@Data
@NoArgsConstructor
@AllArgsConstructor
class AreaDTO {
    String code;
    String name;
    List<AreaDTO> areaList;
}
