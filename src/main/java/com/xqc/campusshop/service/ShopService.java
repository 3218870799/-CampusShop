package com.xqc.campusshop.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xqc.campusshop.dto.ShopExecution;
import com.xqc.campusshop.entity.Shop;

/**
 * 店铺Service
 * @author A Cang（xqc）
 *
 */
public interface ShopService {
		
	/**
	 * 
	 * @param shop
	 * @param shopImg
	 * @return
	 * @throws RuntimeException
	 */
	ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException;
	
	/**
	 * 查询指定店铺信息
	 * 
	 * @param long
	 *            shopId
	 * @return Shop shop
	 */
	Shop getByShopId(long shopId);
	
	/**
	 * 更新店铺信息（从店家角度）
	 * 
	 * @param areaId
	 * @param shopAddr
	 * @param phone
	 * @param shopImg
	 * @param shopDesc
	 * @return
	 * @throws RuntimeException
	 */
	ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException;
}
