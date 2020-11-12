package com.sport.modules.sport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sport.common.utils.PageResult;
import com.sport.modules.sport.dto.StudentDTO;
import com.sport.modules.sport.entity.Student;

import java.util.Map;

/**
 * 学员信息表
 *
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
public interface StudentService extends IService<Student> {

    PageResult queryPage(Map<String, Object> params);

    StudentDTO getStudentWithSchoolNameById(Integer studentId);

    String getStudentNumber();
}

