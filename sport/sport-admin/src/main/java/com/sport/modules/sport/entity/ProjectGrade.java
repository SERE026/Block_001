package com.sport.modules.sport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 项目成绩表
 * 
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
@Data
@TableName("sport_project_grade")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProjectGrade implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 学号
	 */
	private Integer studentId;
	private Integer stuGradeId;

	private Integer age;

	/**
	 * 年龄段
	 */
	private String ageRange;
	/**
	 * 测试员
	 */
	private String teacherName;
	/**
	 * 项目ID
	 */
	private Integer projectId;
	/**
	 * 项目测试成绩
	 */
	private BigDecimal projectGrade;
	/**
	 * 项目配置ID
	 */
	private Integer projectConfigId;
	/**
	 * 检测时间
	 */
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private LocalDateTime checkTime;
	/**
	 * 综合评分
	 */
	private BigDecimal score;
	/**
	 * 评测建议
	 */
	private String suggestion;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private LocalDateTime updateTime;

}
