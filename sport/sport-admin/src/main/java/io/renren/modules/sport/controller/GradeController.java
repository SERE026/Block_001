package io.renren.modules.sport.controller;

import com.alibaba.fastjson.JSON;
import io.renren.common.utils.Result;
import io.renren.modules.sport.dto.GradeParam;
import io.renren.modules.sport.service.GradeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * 学员成绩表
 *
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
@Slf4j
@RestController
@RequestMapping("sport/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;


    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sport:studentgrade:save")
    public Result save(HttpServletRequest request, @RequestBody GradeParam grade){
        log.info("保存成绩信息:{}", JSON.toJSONString(grade));
        System.out.println("--------------");
        return gradeService.saveGrade(grade);
    }

    /**
     *
     */
    @RequestMapping("/info")
    @RequiresPermissions("sport:studentgrade:info")
    public Result detail(Integer studentId){
        return gradeService.queryDetail(studentId);
    }

    @RequestMapping("/page")
    @RequiresPermissions("sport:studentgrade:info")
    public ModelAndView page(Integer studentId){
        ModelAndView mv = new ModelAndView("modules/sport/exportStuGrade");
        Result result = gradeService.queryDetail(studentId);
        mv.addObject("studentId",studentId);
        mv.addAllObjects(result);
        return mv;
    }


}
