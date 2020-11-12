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

import com.sport.modules.sport.entity.TrainGoal;
import com.sport.modules.sport.service.TrainGoalService;


/**
 * 年龄段训练目标
 *
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
@RestController
@RequestMapping("sport/traingoal")
public class TrainGoalController {
    @Autowired
    private TrainGoalService trainGoalService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sport:traingoal:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageResult page = trainGoalService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sport:traingoal:info")
    public Result info(@PathVariable("id") Integer id){
        TrainGoal trainGoal = trainGoalService.getById(id);

        return Result.ok().put("trainGoal", trainGoal);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sport:traingoal:save")
    public Result save(@RequestBody TrainGoal trainGoal){
        trainGoal.setCreateTime(LocalDateTime.now());
        trainGoalService.save(trainGoal);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sport:traingoal:update")
    public Result update(@RequestBody TrainGoal trainGoal){
        ValidatorUtils.validateEntity(trainGoal);
        trainGoalService.updateById(trainGoal);
        
        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sport:traingoal:delete")
    public Result delete(@RequestBody Integer[] ids){
        trainGoalService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

}
