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

import com.sport.modules.sport.entity.School;
import com.sport.modules.sport.service.SchoolService;


/**
 * 
 *
 * @author ó¯òë
 * @email ${email}
 * @date 2019-05-02 01:23:09
 */
@RestController
@RequestMapping("sport/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sport:school:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageResult page = schoolService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sport:school:info")
    public Result info(@PathVariable("id") Integer id){
        School school = schoolService.getById(id);

        return Result.ok().put("school", school);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sport:school:save")
    public Result save(@RequestBody School school){
        school.setCreateTime(LocalDateTime.now());
        schoolService.save(school);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sport:school:update")
    public Result update(@RequestBody School school){
        ValidatorUtils.validateEntity(school);
        schoolService.updateById(school);
        
        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sport:school:delete")
    public Result delete(@RequestBody Integer[] ids){
        schoolService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

}
