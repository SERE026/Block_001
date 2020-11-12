package com.sport.modules.sport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 
 * 
 * @author ó¯òë
 * @email ${email}
 * @date 2019-05-02 01:23:09
 */
@Data
@TableName("sport_school")
public class School implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Integer id;
	/**
	 * 上级小区
	 */
	private Integer parentId;
	/**
	 * 学校名称
	 */
	private String schoolName;
	/**
	 * 学校编码
	 */
	private String schoolCode;
	/**
	 * 学校所在省
	 */
	private String province;
	/**
	 * 学校所在市
	 */
	private String city;
	/**
	 * 学校详细地址
	 */
	private String address;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

}
