package com.sport.modules.sport.dao;

import com.sport.modules.sport.entity.ProjectGrade;
import com.sport.modules.sport.dto.ProjectGradeDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 项目成绩表
 * 
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
@Mapper
public interface ProjectGradeMapper extends BaseMapper<ProjectGrade> {

    List<ProjectGradeDTO> getInGradeIds(@Param("gradeIds")Collection<Integer> gradeIds);
}
