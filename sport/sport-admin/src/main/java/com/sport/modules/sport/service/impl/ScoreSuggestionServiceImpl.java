package com.sport.modules.sport.service.impl;

import com.sport.common.utils.PageResult;
import com.sport.common.utils.Query;
import com.sport.modules.sport.dao.ScoreSuggestionMapper;
import com.sport.modules.sport.entity.ScoreSuggestion;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sport.modules.sport.service.ScoreSuggestionService;


@Service("scoreSuggestionService")
public class ScoreSuggestionServiceImpl extends ServiceImpl<ScoreSuggestionMapper, ScoreSuggestion> implements ScoreSuggestionService {

    @Override
    public PageResult queryPage(Map<String, Object> params) {
        IPage<ScoreSuggestion> page = this.page(
                new Query<ScoreSuggestion>().getPage(params),
                new QueryWrapper<ScoreSuggestion>()
        );

        return new PageResult(page);
    }

    @Override
    public ScoreSuggestion getByProjectCodeWithScoreRange(String projectCode, BigDecimal scoreLevel) {
        return this.baseMapper.getByProjectCodeWithScoreRange(projectCode,scoreLevel);
    }

}
