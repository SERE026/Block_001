package com.sport.modules.sport.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AgeUtils {

    public static String getAgeRange(Integer age){
        if(age >= 3 && age <=5){
            return "3-5";
        }
        if(age >= 6 && age <=8){
            return  "6-8";
        }
        if(age >= 9 && age <=11){
            return "9-11";
        }
        return "未知";
    }

    public static Integer getAgeByBirthday(String birthday) {
        LocalDateTime birth = LocalDateTime.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime now = LocalDateTime.now();
        Integer age = now.getYear() - birth.getYear();
        return age<=0 ? 0 : age;
    }

    public static Integer getAgeByBirthday(LocalDateTime birthday) {
        LocalDateTime now = LocalDateTime.now();
        Integer age = now.getYear() - birthday.getYear();
        return age<=0 ? 0 : age;
    }
}
