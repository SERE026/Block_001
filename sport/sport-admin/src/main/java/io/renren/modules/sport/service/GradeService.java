package io.renren.modules.sport.service;

import io.renren.common.utils.Result;
import io.renren.modules.sport.dto.GradeParam;

public interface GradeService {
    Result saveGrade(GradeParam grade);

    Result queryDetail(Integer studentId);
}
