package com.xqc.campusshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xqc.campusshop.entity.ShopCategory;

/**
 * 店铺类别数据访问层接口
 * @author A Cang（xqc）
 *
 */
public interface ShopCategoryDao {
	/**
	 * 查询店铺类别
	 * @param shopCategory
	 * @return
	 */
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition")
	ShopCategory shopCategory);
	
	/**
	 * 通过类别Id查询单个店铺类别
	 * @param shopCategoryId
	 * @return
	 */
	ShopCategory queryShopCategoryById(long shopCategoryId);

	/**
	 * 通过类别Id查询多个商品类别
	 * @param shopCategoryIdList
	 * @return
	 */
	List<ShopCategory> queryShopCategoryByIds(List<Long> shopCategoryIdList);

	/**
	 * 插入商品类别
	 * @param shopCategory
	 * @return
	 */
	int insertShopCategory(ShopCategory shopCategory);

	/**
	 * 修改商品类别信息
	 * @param shopCategory
	 * @return
	 */
	int updateShopCategory(ShopCategory shopCategory);

	/**
	 * 删除商品类别信息
	 * @param shopCategoryId
	 * @return
	 */
	int deleteShopCategory(long shopCategoryId);

	/**
	 * 批量删除
	 * @param shopCategoryIdList
	 * @return
	 */
	int batchDeleteShopCategory(List<Long> shopCategoryIdList);
	
}
