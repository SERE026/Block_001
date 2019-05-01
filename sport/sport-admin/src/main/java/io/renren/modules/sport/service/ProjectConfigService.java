package io.renren.modules.sport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageResult;
import io.renren.modules.sport.entity.ProjectConfig;

import java.util.List;
import java.util.Map;

/**
 * 训练项目
 *
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 19:48:44
 */
public interface ProjectConfigService extends IService<ProjectConfig> {

    PageResult queryPage(Map<String, Object> params);

    List<ProjectConfig> getByIds(List<Integer> projectIds);

    List<ProjectConfig> getByProjectIds(List<Integer> projectIds);
}

