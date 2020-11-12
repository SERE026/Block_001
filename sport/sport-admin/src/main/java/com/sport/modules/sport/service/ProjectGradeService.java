package com.sport.modules.sport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sport.common.utils.PageResult;
import com.sport.modules.sport.dto.ProjectGradeDTO;
import com.sport.modules.sport.entity.ProjectGrade;

import java.util.List;
import java.util.Map;

/**
 * 项目成绩表
 *
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
public interface ProjectGradeService extends IService<ProjectGrade> {

    PageResult queryPage(Map<String, Object> params);

    List<ProjectGradeDTO> getInGradeIds(List<Integer> gradeIds);

    ProjectGrade getByStuGradeIdWithProjectId(Integer stuGradeId, Integer projectId);
}

