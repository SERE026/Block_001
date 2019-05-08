package io.renren.modules.sport.dao;

import io.renren.modules.sport.entity.ProjectConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 训练项目
 * 
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 19:48:44
 */
@Mapper
public interface ProjectConfigMapper extends BaseMapper<ProjectConfig> {

    List<ProjectConfig> getByProjectIds(@Param("projectIds") List<Integer> projectIds);

    List<ProjectConfig> getByFullScoreByProjectIds(@Param("projectIds")List<Integer> projectIds);
}
