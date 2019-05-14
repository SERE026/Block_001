package io.renren.modules.sport.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageResult;
import io.renren.common.utils.Query;

import io.renren.modules.sport.dao.ProjectConfigMapper;
import io.renren.modules.sport.entity.ProjectConfig;
import io.renren.modules.sport.service.ProjectConfigService;


@Service("projectConfigService")
public class ProjectConfigServiceImpl extends ServiceImpl<ProjectConfigMapper, ProjectConfig> implements ProjectConfigService {

    @Override
    public PageResult queryPage(Map<String, Object> params) {
        Integer age = params.get("age") != null ? (Integer) params.get("age") : 0;
        Integer gender = params.get("gender") != null ? (Integer) params.get("gender") : 0;
        String projectName = (String) params.get("projectName");
        IPage<ProjectConfig> page = this.page(
                new Query<ProjectConfig>().getPage(params),
                new QueryWrapper<ProjectConfig>()
                        .ge(age !=0,"min_age",age)
                        .le(age !=0,"max_age",age)
                        .eq(gender != 0,"gender",gender)
                        .eq(StringUtils.isNotBlank(projectName),"project_name",projectName)
        );

        return new PageResult(page);
    }

    @Override
    public List<ProjectConfig> getByIds(List<Integer> projectIds) {
        return this.baseMapper.selectBatchIds(projectIds);
    }

    @Override
    public List<ProjectConfig> getByProjectIds(List<Integer> projectIds) {
        return this.baseMapper.getByProjectIds(projectIds);
    }

    @Override
    public List<ProjectConfig> getByFullScoreByProjectIds(List<Integer> projectIds) {
        return this.baseMapper.getByFullScoreByProjectIds(projectIds);
    }

    @Override
    public ProjectConfig getByAgeWithGradeRange(Integer projectId, BigDecimal proGrade, Integer age, Integer gender) {
        return this.baseMapper.getByAgeWithGradeRange(projectId,proGrade,age,gender);
    }

}
