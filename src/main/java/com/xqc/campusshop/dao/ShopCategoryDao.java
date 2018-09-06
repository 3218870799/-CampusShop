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

}
