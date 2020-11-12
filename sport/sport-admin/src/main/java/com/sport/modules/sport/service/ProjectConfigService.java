package com.sport.modules.sport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sport.common.utils.PageResult;
import com.sport.modules.sport.entity.ProjectConfig;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 训练项目
 *
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 19:48:44
 */
public interface ProjectConfigService extends IService<ProjectConfig> {

    PageResult queryPage(Map<String, Object> params);

    List<ProjectConfig> getByIds(List<Integer> projectIds);

    List<ProjectConfig> getByProjectIds(List<Integer> projectIds);

    List<ProjectConfig> getByFullScoreByProjectIds(List<Integer> projectIds,Integer age, Integer gender);

    ProjectConfig getByAgeWithGradeRange(Integer projectId, BigDecimal proGrade, Integer age, Integer gender);

}

