package com.xqc.campusshop.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqc.campusshop.BaseTest;
import com.xqc.campusshop.entity.ProductSellDaily;
import com.xqc.campusshop.entity.Shop;

public class ProductSellDailyDaoTest extends BaseTest{
	
	@Autowired
	private ProductSellDailyDao productSellDailyDao;
	
	@Test
	@Ignore
	public void testInsertProductSellDaily(){
		int eff = productSellDailyDao.insertProductSellDaily();
		System.out.println(eff);
	}
	
	@Test
	public void testQueryProductSellDailyList(){
		ProductSellDaily productSellDaily = new ProductSellDaily();
		
		Shop shop = new Shop();
		shop.setShopId(29L);
		
		productSellDaily.setShop(shop);
		
		productSellDailyDao.queryProductSellDailyList(productSellDaily,null,null);
		
		
		
		
	}

}
