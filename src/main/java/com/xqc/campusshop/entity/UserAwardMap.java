package com.xqc.campusshop.entity;

import java.util.Date;

/**
 * 顾客领取奖品映射
 * @author A Cang（xqc）
 *
 */
public class UserAwardMap {

	//主键
	private Long userAwardId;
	//创建时间
	private Date createTime;
	//使用状态，0表示未兑换，1表示已兑换
	private Integer usedStatus;
	//领取奖品所消耗的积分
	private Integer point;
	//顾客信息信息实体类
	private PersonInfo user;
	//奖品信息实体类
	private Award award;
	//店铺信息实体类
	private Shop shop;
	//操作员实体类
	private PersonInfo operator;

	public Long getUserAwardId() {
		return userAwardId;
	}

	public void setUserAwardId(Long userAwardId) {
		this.userAwardId = userAwardId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUsedStatus() {
		return usedStatus;
	}

	public void setUsedStatus(Integer usedStatus) {
		this.usedStatus = usedStatus;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public PersonInfo getUser() {
		return user;
	}

	public void setUser(PersonInfo user) {
		this.user = user;
	}

	public Award getAward() {
		return award;
	}

	public void setAward(Award award) {
		this.award = award;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public PersonInfo getOperator() {
		return operator;
	}

	public void setOperator(PersonInfo operator) {
		this.operator = operator;
	}
	

}
