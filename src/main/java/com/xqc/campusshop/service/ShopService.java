package com.xqc.campusshop.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xqc.campusshop.dto.ShopExecution;
import com.xqc.campusshop.entity.Shop;

public interface ShopService {
	
	/**
	 * 
	 * @param shop
	 * @param shopImg
	 * @return
	 * @throws RuntimeException
	 */
	ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException;
}
