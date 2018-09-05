package com.xqc.campusshop.service;

import java.util.List;

import com.xqc.campusshop.entity.ProductCategory;

/**
 * 商品类别Service层接口
 * @author A Cang（xqc）
 *
 */
public interface ProductCategoryService {
	
	/**
	 * 根据店铺id获取店铺商品类别列表
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> getProductCategoryList(long shopId);

}
