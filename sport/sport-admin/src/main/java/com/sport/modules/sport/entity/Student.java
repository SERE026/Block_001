package com.sport.modules.sport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 学员信息表
 * 
 * @author ó¯òë
 * @email ${email}
 * @date 2019-04-25 15:56:20
 */
@Data
@TableName("sport_student")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 学号
	 */
	private String stuNumber;
	/**
	 * 联系方式
	 */
	private String mobile;
	/**
	 * 学生姓名
	 */
	private String realname;
	/**
	 * 性别：1：男 2：女
	 */
	private Integer gender;
	/**
	 * 出生日期
	 */
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private LocalDateTime birthday;
	/**
	 * 家长姓名
	 */
	private String familyName;
	/**
	 * 家长联系方式
	 */
	private String familyMobile;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 注册时间/报名时间
	 */
	@JsonFormat( pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private LocalDateTime registerTime;
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

	private String province;
	private String city;
	private String address;
	private Integer schoolId;
	private Integer gradeFlag;

}
