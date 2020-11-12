package com.sport.modules.sport.service.impl;

import com.sport.common.utils.PageResult;
import com.sport.common.utils.Query;
import com.sport.modules.sport.dao.ProjectMapper;
import com.sport.modules.sport.entity.Project;
import com.sport.modules.sport.util.AgeUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sport.modules.sport.service.ProjectService;

import javax.annotation.Resource;


@Service("projectService")
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    @Override
    public PageResult queryPage(Map<String, Object> params) {
        IPage<Project> page = this.page(
                new Query<Project>().getPage(params),
                new QueryWrapper<Project>()
        );

        return new PageResult(page);
    }

    @Override
    public List<Project> getProjectByBirthday(String birthday) {
        Integer age = AgeUtils.getAgeByBirthday(birthday);
        List<Project> list = this.list();
        return list.stream().filter(p -> p.getMinAge() <= age && p.getMaxAge() >=age)
                .collect(Collectors.toList());
    }

}
