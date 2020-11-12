/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.sport.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * 注册表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class RegisterForm {
    @NotBlank(message="手机号不能为空")
    private String mobile;

    @NotBlank(message="密码不能为空")
    private String password;

}
