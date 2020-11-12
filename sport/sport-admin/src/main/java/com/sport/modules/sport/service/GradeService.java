package com.sport.modules.sport.service;

import com.sport.common.utils.Result;
import com.sport.modules.sport.dto.GradeParam;

public interface GradeService {
    Result saveGrade(GradeParam grade);
    Result getLastGrade(Integer studentId);
    Result queryDetail(Integer studentId);

    Result updateGrade(GradeParam grade);
}
