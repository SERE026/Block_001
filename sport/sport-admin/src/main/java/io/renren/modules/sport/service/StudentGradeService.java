package io.renren.modules.sport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageResult;
import io.renren.modules.sport.entity.StudentGrade;

import java.util.List;
import java.util.Map;

/**
 * 学员成绩表
 *
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
public interface StudentGradeService extends IService<StudentGrade> {

    PageResult queryPage(Map<String, Object> params);

    List<StudentGrade> getLastTwoGrade(Integer studentId);

    StudentGrade getLastGrade(Integer studentId);
}

