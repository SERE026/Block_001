package io.renren.modules.sport.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sport.entity.Project;
import io.renren.modules.sport.service.ProjectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sport.entity.ProjectConfig;
import io.renren.modules.sport.service.ProjectConfigService;
import io.renren.common.utils.PageResult;
import io.renren.common.utils.Result;



/**
 * 训练项目
 *
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 19:48:44
 */
@RestController
@RequestMapping("sport/projectconfig")
public class ProjectConfigController {
    @Autowired
    private ProjectConfigService projectConfigService;
    @Autowired
    private ProjectService projectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sport:projectconfig:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageResult page = projectConfigService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sport:projectconfig:info")
    public Result info(@PathVariable("id") Integer id){
        ProjectConfig projectConfig = projectConfigService.getById(id);

        return Result.ok().put("projectConfig", projectConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sport:projectconfig:save")
    public Result save(@RequestBody ProjectConfig projectConfig){

        Project project = projectService.getById(projectConfig.getProjectId());
        projectConfig.setProjectName(project.getProjectName())
                .setMaxAge(projectConfig.getMaxAge())
                .setProjectType(project.getProjectType())
                .setRule(project.getRule())
                .setCreateTime(LocalDateTime.now());
        projectConfigService.save(projectConfig);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sport:projectconfig:update")
    public Result update(@RequestBody ProjectConfig projectConfig){
        ValidatorUtils.validateEntity(projectConfig);
        projectConfigService.updateById(projectConfig);
        
        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sport:projectconfig:delete")
    public Result delete(@RequestBody Integer[] ids){
        projectConfigService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

}
