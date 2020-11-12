package com.sport.modules.sport.dto;

import com.sport.modules.sport.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO extends Student {
    private Integer age;
    private String schoolName;
}
