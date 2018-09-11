package com.xqc.campusshop.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqc.campusshop.BaseTest;
import com.xqc.campusshop.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest {
	
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Test
	public void testQueryShopCategory() throws Exception {
		
		List<ShopCategory> shopCategoryList = shopCategoryDao
				.queryShopCategory(null);
		System.out.println(shopCategoryList.size());		
	
	}

}
