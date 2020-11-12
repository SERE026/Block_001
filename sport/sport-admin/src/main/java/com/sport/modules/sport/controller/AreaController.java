package com.sport.modules.sport.controller;

import java.util.Arrays;
import java.util.List;
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

import com.sport.modules.sport.entity.Area;
import com.sport.modules.sport.service.AreaService;


/**
 * 
 *
 * @author ó¯òë
 * @email ${email}
 * @date 2019-05-06 22:34:15
 */
@RestController
@RequestMapping("sport/area")
public class AreaController {
    @Autowired
    private AreaService areaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sport:area:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageResult page = areaService.queryPage(params);

        return Result.ok().put("page", page);
    }

    @RequestMapping("/listByParentId")
    @RequiresPermissions("sport:area:list")
    public Result list(@RequestParam Integer parentId){
        List<Area> list = areaService.listByParentId(parentId);
        return Result.ok().put("areaList", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sport:area:info")
    public Result info(@PathVariable("id") Integer id){
        Area area = areaService.getById(id);

        return Result.ok().put("area", area);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sport:area:save")
    public Result save(@RequestBody Area area){
        areaService.save(area);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sport:area:update")
    public Result update(@RequestBody Area area){
        ValidatorUtils.validateEntity(area);
        areaService.updateById(area);
        
        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sport:area:delete")
    public Result delete(@RequestBody Integer[] ids){
        areaService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

}
