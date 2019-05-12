package io.renren.modules.sport.dao;

import io.renren.modules.sport.entity.BmiGrade;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * BMI成绩表
 * 
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
@Mapper
public interface BmiGradeMapper extends BaseMapper<BmiGrade> {

    List<BmiGrade> selectByStudentId(Integer studentId);

    BmiGrade selectLastByStuGradeId(Integer stuGradeId);
}
