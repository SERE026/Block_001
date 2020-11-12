package com.sport.modules.sport.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeParam implements Serializable {
    private Integer studentId;
    private String checkTime;
    private String teacherName;
    private BigDecimal height;
    private BigDecimal weight;
    private String trainHours;
    private BigDecimal attendance;
    List<ProGradeParam> proList;
}


