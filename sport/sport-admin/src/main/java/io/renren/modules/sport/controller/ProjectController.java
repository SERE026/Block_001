package io.renren.modules.sport.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sport.entity.Project;
import io.renren.modules.sport.service.ProjectService;
import io.renren.common.utils.PageResult;
import io.renren.common.utils.Result;



/**
 * 项目
 *
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
@RestController
@RequestMapping("sport/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sport:project:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageResult page = projectService.queryPage(params);

        return Result.ok().put("page", page);
    }

    @RequestMapping("/listByBirthday")
    @RequiresPermissions("sport:project:listByBirthday")
    public Result listByAge(String birthday){
        List<Project> list = projectService.getProjectByBirthday(birthday);
        return Result.ok().put("data", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sport:project:info")
    public Result info(@PathVariable("id") Integer id){
        Project project = projectService.getById(id);

        return Result.ok().put("project", project);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sport:project:save")
    public Result save(@RequestBody Project project){
        projectService.save(project);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sport:project:update")
    public Result update(@RequestBody Project project){
        ValidatorUtils.validateEntity(project);
        projectService.updateById(project);
        
        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sport:project:delete")
    public Result delete(@RequestBody Integer[] ids){
        projectService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

}
