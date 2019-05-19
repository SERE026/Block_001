package io.renren.modules.sport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 训练项目
 * 
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 19:48:44
 */
@Data
@TableName("sport_project_config")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class ProjectConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 训练目标
	 */
	private String projectType;
	/**
	 * 项目ID
	 */
	private Integer projectId;
	/**
	 * 训练项目
	 */
	private String projectName;
	/**
	 * 分值范围最低值
	 */
	private BigDecimal minScore;
	/**
	 * 分值范围最高值
	 */
	private BigDecimal maxScore;
	/**
	 * 综合评分级别
	 */
	private BigDecimal scoreLevel;
	/**
	 * 年龄
	 */
	private Integer minAge;
	private Integer maxAge;
	/**
	 * 性别
	 */
	private Integer gender;
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
	/**
	 * 1：分值越大评分越高 2：分值越大评分越低
	 */
	private Integer rule;

}
