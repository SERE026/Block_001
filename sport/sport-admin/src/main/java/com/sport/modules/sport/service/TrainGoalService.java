package com.sport.modules.sport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sport.common.utils.PageResult;
import com.sport.modules.sport.entity.TrainGoal;

import java.util.List;
import java.util.Map;

/**
 * 年龄段训练目标
 *
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
public interface TrainGoalService extends IService<TrainGoal> {

    PageResult queryPage(Map<String, Object> params);

    List<TrainGoal> queryAll();
}

