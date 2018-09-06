package com.xqc.campusshop.service;

import java.util.List;

import com.xqc.campusshop.dto.ProductCategoryExecution;
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
	
	/**
	 * 批量添加商品类别
	 * @param productCategoryList
	 * @return
	 * @throws RuntimeException
	 */
	ProductCategoryExecution batchAddProductCategory(
			List<ProductCategory> productCategoryList) throws RuntimeException;
	
	/**
	 * 删除产品类别，如果商品类别下有商品则清空
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws RuntimeException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId,
			long shopId) throws RuntimeException;

}
