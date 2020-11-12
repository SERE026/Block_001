package com.sport.modules.sport.service.impl;

import com.sport.common.utils.PageResult;
import com.sport.common.utils.Query;
import com.sport.modules.sport.service.BmiConfigService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sport.modules.sport.dao.BmiConfigMapper;
import com.sport.modules.sport.entity.BmiConfig;


@Service("bmiConfigService")
public class BmiConfigServiceImpl extends ServiceImpl<BmiConfigMapper, BmiConfig> implements BmiConfigService {

    @Override
    public PageResult queryPage(Map<String, Object> params) {
        IPage<BmiConfig> page = this.page(
                new Query<BmiConfig>().getPage(params),
                new QueryWrapper<BmiConfig>()
        );

        return new PageResult(page);
    }

}
