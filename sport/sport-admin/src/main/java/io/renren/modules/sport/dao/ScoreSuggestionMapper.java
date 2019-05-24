package io.renren.modules.sport.dao;

import io.renren.modules.sport.entity.ScoreSuggestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * 评分建议表
 * 
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
@Mapper
public interface ScoreSuggestionMapper extends BaseMapper<ScoreSuggestion> {

    ScoreSuggestion getByProjectCodeWithScoreRange(@Param("projectCode") String projectCode, @Param("scoreLevel") BigDecimal scoreLevel);
}
