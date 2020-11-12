package com.sport.modules.sport.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

import com.sport.common.utils.PageResult;
import com.sport.common.utils.Result;
import com.sport.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sport.modules.sport.entity.ProjectGrade;
import com.sport.modules.sport.service.ProjectGradeService;


/**
 * 项目成绩表
 *
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
@RestController
@RequestMapping("sport/projectgrade")
public class ProjectGradeController {
    @Autowired
    private ProjectGradeService projectGradeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sport:projectgrade:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageResult page = projectGradeService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sport:projectgrade:info")
    public Result info(@PathVariable("id") Integer id){
        ProjectGrade projectGrade = projectGradeService.getById(id);

        return Result.ok().put("projectGrade", projectGrade);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sport:projectgrade:save")
    public Result save(@RequestBody ProjectGrade projectGrade){
        projectGrade.setCreateTime(LocalDateTime.now());
        projectGradeService.save(projectGrade);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sport:projectgrade:update")
    public Result update(@RequestBody ProjectGrade projectGrade){
        ValidatorUtils.validateEntity(projectGrade);
        projectGradeService.updateById(projectGrade);
        
        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sport:projectgrade:delete")
    public Result delete(@RequestBody Integer[] ids){
        projectGradeService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

}
