/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.sport.controller;

import com.sport.annotation.Login;
import com.sport.annotation.LoginUser;
import com.sport.common.utils.Result;
import com.sport.entity.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/api")
public class ApiTestController {

    @Login
    @GetMapping("userInfo")
    public Result userInfo( @LoginUser UserEntity user){
        return Result.ok().put("user", user);
    }

    @Login
    @GetMapping("userId")
    public Result userInfo( @RequestAttribute("userId") Integer userId){
        return Result.ok().put("userId", userId);
    }

    @GetMapping("notToken")
    public Result notToken(){
        return Result.ok().put("msg", "无需token也能访问。。。");
    }

}
