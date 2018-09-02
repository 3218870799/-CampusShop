package com.xqc.campusshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqc.campusshop.dao.ShopCategoryDao;
import com.xqc.campusshop.entity.ShopCategory;
import com.xqc.campusshop.service.ShopCategoryService;

@Service
public class ShopCategorySeviceImpl implements ShopCategoryService{
	
	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
		
		return shopCategoryDao.queryShopCategory(shopCategoryCondition);
	}
	
	

}
