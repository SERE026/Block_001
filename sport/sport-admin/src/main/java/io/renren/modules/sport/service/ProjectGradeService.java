package io.renren.modules.sport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageResult;
import io.renren.modules.sport.dto.ProjectGradeDTO;
import io.renren.modules.sport.entity.ProjectGrade;

import java.util.List;
import java.util.Map;

/**
 * 项目成绩表
 *
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
public interface ProjectGradeService extends IService<ProjectGrade> {

    PageResult queryPage(Map<String, Object> params);

    List<ProjectGradeDTO> getInGradeIds(List<Integer> gradeIds);

    ProjectGrade getByStuGradeIdWithProjectId(Integer stuGradeId, Integer projectId);
}

