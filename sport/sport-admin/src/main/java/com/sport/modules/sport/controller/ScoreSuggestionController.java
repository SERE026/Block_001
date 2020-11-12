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

import com.sport.modules.sport.entity.ScoreSuggestion;
import com.sport.modules.sport.service.ScoreSuggestionService;


/**
 * 评分建议表
 *
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
@RestController
@RequestMapping("sport/scoresuggestion")
public class ScoreSuggestionController {
    @Autowired
    private ScoreSuggestionService scoreSuggestionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sport:scoresuggestion:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageResult page = scoreSuggestionService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sport:scoresuggestion:info")
    public Result info(@PathVariable("id") Integer id){
        ScoreSuggestion scoreSuggestion = scoreSuggestionService.getById(id);

        return Result.ok().put("scoreSuggestion", scoreSuggestion);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sport:scoresuggestion:save")
    public Result save(@RequestBody ScoreSuggestion scoreSuggestion){
        scoreSuggestion.setCreateTime(LocalDateTime.now());
        scoreSuggestionService.save(scoreSuggestion);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sport:scoresuggestion:update")
    public Result update(@RequestBody ScoreSuggestion scoreSuggestion){
        ValidatorUtils.validateEntity(scoreSuggestion);
        scoreSuggestionService.updateById(scoreSuggestion);
        
        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sport:scoresuggestion:delete")
    public Result delete(@RequestBody Integer[] ids){
        scoreSuggestionService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

}
