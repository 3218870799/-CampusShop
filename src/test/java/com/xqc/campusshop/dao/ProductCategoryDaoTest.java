package com.xqc.campusshop.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqc.campusshop.BaseTest;
import com.xqc.campusshop.entity.ProductCategory;

public class ProductCategoryDaoTest extends BaseTest{

	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Test
	public void testQueryByShopId(){
		long shopId = 29;
		List<ProductCategory> productCategoryList = productCategoryDao.queryByShopId(shopId);
		
		System.out.println(productCategoryList.toString());
		
	}
}
