package com.xqc.campusshop.entity;

import java.util.Date;
/**
 * 奖品实体类
 * @author A Cang（xqc）
 *
 */
public class Award {
	//奖品ID
	private Long awardId;
	//奖品名称
	private String awardName;
	//奖品描述
	private String awardDesc;
	//奖品图片
	private String awardImg;
	//奖品积分点
	private Integer point;
	//奖品权重
	private Integer priority;
	//创建时间
	private Date createTime;
	//过期时间
	private Date expireTime;
	//最后编辑时间
	private Date lastEditTime;
	//状态
	private Integer enableStatus;
	//店铺Id
	private Long shopId;

	public Long getAwardId() {
		return awardId;
	}

	public void setAwardId(Long awardId) {
		this.awardId = awardId;
	}

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public String getAwardDesc() {
		return awardDesc;
	}

	public void setAwardDesc(String awardDesc) {
		this.awardDesc = awardDesc;
	}

	public String getAwardImg() {
		return awardImg;
	}

	public void setAwardImg(String awardImg) {
		this.awardImg = awardImg;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

}
