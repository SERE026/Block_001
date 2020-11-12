package com.sport.modules.sport.constants;


public interface SportConstants{

    enum GenderEnum{
        MALE(1,"男"),FEMALE(2,"女"),UNKNOW(0,"未知");
        GenderEnum(Integer val,String desc){
            this.val = val;
            this.desc = desc;
        };
        private Integer val;
        private String desc;

        public Integer getVal() {
            return val;
        }

        public void setVal(Integer val) {
            this.val = val;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
    /**
     * 年龄段
     */
    enum AgeRangeEnum {

        AGE_35(1,"3-5"),AGE_68(2,"6-8"),AGE_911(3,"9-11");
        AgeRangeEnum(Integer age,String desc){
            this.age = age;
            this.desc = desc;
        }
        private Integer age;
        private String desc;

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }}
}

