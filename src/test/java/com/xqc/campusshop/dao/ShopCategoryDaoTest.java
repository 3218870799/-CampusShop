package com.xqc.campusshop.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqc.campusshop.BaseTest;
import com.xqc.campusshop.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest {
	
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Test
	@Ignore
	public void testQueryShopCategory() throws Exception {
		
		List<ShopCategory> shopCategoryList = shopCategoryDao
				.queryShopCategory(null);
		System.out.println(shopCategoryList.size());		
	
	}
	
	@Test
	@Ignore
	public void testQueryShopCategoryById(){
		ShopCategory shopCategory = shopCategoryDao.queryShopCategoryById(33L);
		System.out.println(shopCategory.getShopCategoryName());
	}
	
	@Test
	public void testQueryShopCategoryByIds(){
		List<Long> shopCategoryIdList = new ArrayList<Long>();
		shopCategoryIdList.add(33L);
		shopCategoryIdList.add(34L);
		shopCategoryIdList.add(35L);
		
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategoryByIds(shopCategoryIdList);
		System.out.println(shopCategoryIdList.size());
	}
	
	@Test
	@Ignore
	public void testInsertShopCategory(){
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryName("testInsert");
		shopCategory.setShopCategoryDesc("testInsertDesc");
		shopCategory.setPriority(2);
		shopCategory.setCreateTime(new Date());
		shopCategory.setLastEditTime(new Date());
		shopCategory.setParentId(33L);
		int eff = shopCategoryDao.insertShopCategory(shopCategory);
		System.out.println(eff);
		
	}
	
	@Test
	@Ignore
	public void testUpdateShopCategory(){
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(35L);
		shopCategory.setShopCategoryDesc("updateTest");
		shopCategory.setPriority(3);
		shopCategory.setLastEditTime(new Date());
		
		int eff = shopCategoryDao.updateShopCategory(shopCategory);
		System.out.println(eff);
	}

}
