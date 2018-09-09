package com.xqc.campusshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xqc.campusshop.entity.Product;

/**
 * 商品数据访问层
 * @author A Cang（xqc）
 *
 */
public interface ProductDao {
	
	/**
	 * 查询商品列表并分页，可输入的条件有：商品名（模糊），商品状态，店铺Id,商品类别
	 * 
	 * @param productCondition
	 * @param beginIndex
	 * @param pageSize
	 * @return
	 */
	List<Product> queryProductList(
			@Param("productCondition") Product productCondition,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	/**
	 * 插入商品
	 * 
	 * @param product
	 * @return
	 */
	int insertProduct(Product product);
	/**
	 * 查询对应的商品总数，分页时使用
	 * 
	 * @param productCondition
	 * @return
	 */
	int queryProductCount(@Param("productCondition") Product productCondition);

	/**
	 * 根据商品id查询商品
	 * @param productId
	 * @return
	 */

	Product queryProductByProductId(long productId);

	/**
	 * 更新商品信息
	 * 
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);
	/**
	 * 删除商品类别前，将商品类别ID置空
	 * @param productCategoryId
	 * @return
	 */
	
	int updateProductCategoryToNull(long productCategoryId);

	/**
	 * 删除商品
	 * 
	 * @param productId
	 * @return
	 */
	int deleteProduct(@Param("productId") long productId,
			@Param("shopId") long shopId);
	
	
	
	
	

}
