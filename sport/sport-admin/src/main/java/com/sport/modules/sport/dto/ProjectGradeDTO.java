package com.sport.modules.sport.dto;

import com.sport.modules.sport.entity.ProjectGrade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectGradeDTO extends ProjectGrade {
    private String projectCode;
    private String projectType;
    private String projectName;
}
