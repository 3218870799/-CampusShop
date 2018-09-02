package com.xqc.campusshop.service;

import java.util.List;

import com.xqc.campusshop.entity.ShopCategory;

public interface ShopCategoryService {
	
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
	

}
