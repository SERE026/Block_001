package com.sport.modules.sport.dao;

import com.sport.modules.sport.entity.Student;
import com.sport.modules.sport.dto.StudentDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学员信息表
 * 
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    StudentDTO getStudentWithSchoolNameById(Integer studentId);

    String getStudentNumber();
}
