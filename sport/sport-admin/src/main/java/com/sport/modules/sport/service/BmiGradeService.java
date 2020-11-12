package com.sport.modules.sport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sport.common.utils.PageResult;
import com.sport.modules.sport.entity.BmiGrade;

import java.util.List;
import java.util.Map;

/**
 * BMI成绩表
 *
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
public interface BmiGradeService extends IService<BmiGrade> {

    PageResult queryPage(Map<String, Object> params);

    List<BmiGrade> getByStudentId(Integer studentId);

    BmiGrade getLastByStuGradeId(Integer stuGradeId);
}

