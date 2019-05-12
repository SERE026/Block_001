package io.renren.modules.sport.service.impl;

import io.renren.common.utils.PageResult;
import io.renren.modules.sport.dao.ProjectGradeMapper;
import io.renren.modules.sport.dto.ProjectGradeDTO;
import io.renren.modules.sport.entity.ProjectGrade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.Query;

import io.renren.modules.sport.service.ProjectGradeService;
import org.springframework.util.CollectionUtils;


@Service("projectGradeService")
public class ProjectGradeServiceImpl extends ServiceImpl<ProjectGradeMapper, ProjectGrade> implements ProjectGradeService {

    @Override
    public PageResult queryPage(Map<String, Object> params) {
        IPage<ProjectGrade> page = this.page(
                new Query<ProjectGrade>().getPage(params),
                new QueryWrapper<ProjectGrade>()
        );

        return new PageResult(page);
    }

    @Override
    public List<ProjectGradeDTO> getInGradeIds(List<Integer> gradeIds) {
        return this.baseMapper.getInGradeIds(gradeIds);
    }

    @Override
    public ProjectGrade getByStuGradeIdWithProjectId(Integer stuGradeId, Integer projectId) {
        ProjectGrade pg = ProjectGrade.builder().stuGradeId(stuGradeId).projectId(projectId).build();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.setEntity(pg);
        List<ProjectGrade> list = this.baseMapper.selectList(wrapper);
        return CollectionUtils.isEmpty(list)? null: list.get(0);
    }

}
