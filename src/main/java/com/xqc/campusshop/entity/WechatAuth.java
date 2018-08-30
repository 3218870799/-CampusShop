package com.xqc.campusshop.entity;

import java.util.Date;

/**
 * 微信实体类
 * 
 * @author A Cang（xqc）
 *
 */
public class WechatAuth {
	//ID
	private Long wechatAuthId;
	//外键用户ID
	private Long userId;
	//
	private String openId;
	//创建时间
	private Date createTime;
	//用户实体类关联
	private PersonInfo personInfo;

	public Long getWechatAuthId() {
		return wechatAuthId;
	}

	public void setWechatAuthId(Long wechatAuthId) {
		this.wechatAuthId = wechatAuthId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

}
