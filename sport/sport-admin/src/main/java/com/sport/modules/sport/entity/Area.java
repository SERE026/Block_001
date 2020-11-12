package com.sport.modules.sport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author ó¯òë
 * @email ${email}
 * @date 2019-05-06 22:34:15
 */
@Data
@TableName("sport_area")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Area implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Integer id;
	/**
	 * 父级ID
	 */
	private Integer parentId;
	private String parentCode;
	/**
	 * 区域
	 */
	private String areaName;

	private String areaCode;
	/**
	 * 级别 1：省 2：市
	 */
	private Integer level;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
