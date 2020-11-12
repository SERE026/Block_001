package com.sport.modules.sport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sport.common.utils.PageResult;
import com.sport.common.utils.Query;
import com.sport.modules.sport.dao.TrainGoalMapper;
import com.sport.modules.sport.entity.TrainGoal;
import com.sport.modules.sport.service.TrainGoalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("trainGoalService")
public class TrainGoalServiceImpl extends ServiceImpl<TrainGoalMapper, TrainGoal> implements TrainGoalService {

    @Override
    public PageResult queryPage(Map<String, Object> params) {
        IPage<TrainGoal> page = this.page(
                new Query<TrainGoal>().getPage(params),
                new QueryWrapper<TrainGoal>()
        );

        return new PageResult(page);
    }

    @Override
    public List<TrainGoal> queryAll() {
        List<TrainGoal> list = this.list();
        list.forEach(e ->{
            e.setAgeRange(e.getMinAge()+"-"+e.getMaxAge());
        });
        return list;
    }

}
