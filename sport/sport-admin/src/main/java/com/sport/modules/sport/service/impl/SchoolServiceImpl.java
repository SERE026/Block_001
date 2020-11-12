package com.sport.modules.sport.service.impl;

import com.sport.common.utils.PageResult;
import com.sport.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sport.modules.sport.dao.SchoolMapper;
import com.sport.modules.sport.entity.School;
import com.sport.modules.sport.service.SchoolService;


@Service("schoolService")
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements SchoolService {

    @Override
    public PageResult queryPage(Map<String, Object> params) {
        IPage<School> page = this.page(
                new Query<School>().getPage(params),
                new QueryWrapper<School>()
        );

        return new PageResult(page);
    }

}
