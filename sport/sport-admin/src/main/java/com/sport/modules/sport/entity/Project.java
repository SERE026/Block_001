package com.sport.modules.sport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 项目
 * 
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
@Data
@TableName("sport_project")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 项目类型
	 */
	private String projectType;
	/**
	 * 项目名称
	 */
	private String projectName;
	private String projectCode;

	private Integer minAge;

	private Integer maxAge;
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
