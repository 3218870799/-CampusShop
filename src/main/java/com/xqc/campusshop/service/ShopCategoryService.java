package com.xqc.campusshop.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xqc.campusshop.dto.ShopCategoryExecution;
import com.xqc.campusshop.entity.ShopCategory;

/**
 * 商品类别Service接口
 * @author A Cang（xqc）
 *
 */
public interface ShopCategoryService {
	
	/**
	 * 查询所有店铺类别
	 * @param shopCategoryCondition
	 * @return
	 */
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);

	/**
	 * 通过id查询店铺类别
	 * @param shopCategoryId
	 * @return
	 */
	ShopCategory getShopCategoryById(Long shopCategoryId);

	/**
	 * 获取店铺类别一级列表
	 * @return
	 */
	List<ShopCategory> getFirstLevelShopCategoryList();

	/**
	 * 添加店铺类别信息
	 * @param shopCategory
	 * @param thumbnail
	 * @return
	 */
	ShopCategoryExecution addShopCategory(ShopCategory shopCategory, CommonsMultipartFile thumbnail);

	/**
	 * 修改商铺类别信息
	 * @param shopCategory
	 * @param thumbnail
	 * @param thumbnailChange
	 * @return
	 */
	ShopCategoryExecution modifyShopCategory(ShopCategory shopCategory, CommonsMultipartFile thumbnail,
			boolean thumbnailChange);

	/**
	 * 删除商铺类别信息
	 * @param shopCategoryId
	 * @return
	 */
	ShopCategoryExecution removeShopCategory(Long shopCategoryId);

	/**
	 * 批量删除商品类别
	 * @param shopCategoryIdList
	 * @return
	 */
	ShopCategoryExecution removeShopCategoryList(List<Long> shopCategoryIdList);
	

}
