package com.sport.modules.sport.dao;

import com.sport.modules.sport.entity.StudentGrade;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 学员成绩表
 * 
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
@Mapper
public interface StudentGradeMapper extends BaseMapper<StudentGrade> {

    List<StudentGrade> getByLastTwoStudentId(Integer studentId);

    StudentGrade getByLastStudentId(Integer studentId);
}
