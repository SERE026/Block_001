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

import com.sport.modules.sport.entity.StudentGrade;
import com.sport.modules.sport.service.StudentGradeService;


/**
 * 学员成绩表
 *
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
@RestController
@RequestMapping("sport/studentgrade")
public class StudentGradeController {
    @Autowired
    private StudentGradeService studentGradeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sport:studentgrade:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageResult page = studentGradeService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sport:studentgrade:info")
    public Result info(@PathVariable("id") Integer id){
        StudentGrade studentGrade = studentGradeService.getById(id);

        return Result.ok().put("studentGrade", studentGrade);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sport:studentgrade:save")
    public Result save(@RequestBody StudentGrade studentGrade){
        studentGrade.setCreateTime(LocalDateTime.now());
        studentGradeService.save(studentGrade);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sport:studentgrade:update")
    public Result update(@RequestBody StudentGrade studentGrade){
        ValidatorUtils.validateEntity(studentGrade);
        studentGradeService.updateById(studentGrade);
        
        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sport:studentgrade:delete")
    public Result delete(@RequestBody Integer[] ids){
        studentGradeService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

}
