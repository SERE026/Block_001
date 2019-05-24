package io.renren.modules.sport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageResult;
import io.renren.modules.sport.entity.ScoreSuggestion;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 评分建议表
 *
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
public interface ScoreSuggestionService extends IService<ScoreSuggestion> {

    PageResult queryPage(Map<String, Object> params);

    ScoreSuggestion getByProjectCodeWithScoreRange(String projectCode, BigDecimal scoreLevel);
}

