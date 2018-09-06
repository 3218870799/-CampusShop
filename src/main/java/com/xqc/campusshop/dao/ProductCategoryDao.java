package com.xqc.campusshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xqc.campusshop.entity.ProductCategory;

/**
 * 商品类别数据访问层接口
 * @author A Cang（xqc）
 *
 */
public interface ProductCategoryDao {
	
	/**
	 * 通过employee id 查询店铺
	 * 
	 * @param long shopId
	 * @return List<ProductCategory>
	 */
	List<ProductCategory> queryByShopId(long shopId);
	
	/**
	 * 批量新增商品类别
	 * 
	 * @param ProductCategory
	 *            productCategory
	 * @return effectedNum
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);
	
	/**
	 * 删除商品类别
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	int deleteProductCategory(
			@Param("productCategoryId") long productCategoryId,
			@Param("shopId") long shopId);
	

}
