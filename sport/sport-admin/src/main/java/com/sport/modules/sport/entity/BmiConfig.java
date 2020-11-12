package com.sport.modules.sport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 年龄段体重标准
 * 
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 19:48:44
 */
@Data
@TableName("sport_bmi_config")
public class BmiConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 标准范围
	 */
	private String normWeightRange;
	/**
	 * 低重范围
	 */
	private String lowWeightRange;
	/**
	 * 超重范围
	 */
	private String overWeightRange;
	/**
	 * 肥胖范围
	 */
	private String fatWeightRange;
	/**
	 * 年龄段
	 */
	private Integer minAge;
	/**
	 * 年龄段
	 */
	private Integer maxAge;
	/**
	 * 性别1：男 2：女
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

}
