/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.sport.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sport.common.utils.PageResult;
import com.sport.modules.sys.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageResult queryPage(Map<String, Object> params);

}
