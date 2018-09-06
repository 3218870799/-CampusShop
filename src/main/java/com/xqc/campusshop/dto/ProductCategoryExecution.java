package com.xqc.campusshop.dto;

import java.util.List;

import com.xqc.campusshop.entity.ProductCategory;
import com.xqc.campusshop.enums.ProductCategoryStateEnum;

/**
 * 店铺类型返回类型
 * @author A Cang（xqc）
 *
 */
public class ProductCategoryExecution {
	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	// 操作的商铺类别
	private List<ProductCategory> productCategoryList;

	public ProductCategoryExecution() {
	}

	// 操作失败时使用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 操作成功时使用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum,
			List<ProductCategory> productCategoryList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productCategoryList = productCategoryList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public List<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}

	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}

}
