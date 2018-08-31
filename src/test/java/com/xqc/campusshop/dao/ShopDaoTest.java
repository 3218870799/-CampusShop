package com.xqc.campusshop.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqc.campusshop.BaseTest;
import com.xqc.campusshop.entity.Area;
import com.xqc.campusshop.entity.PersonInfo;
import com.xqc.campusshop.entity.Shop;
import com.xqc.campusshop.entity.ShopCategory;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;

	@Test
	@Ignore
	public void testInsertShop() throws Exception {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		
		owner.setUserId(12L);
		area.setAreaId(1);
		shopCategory.setShopCategoryId(33L);
		
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		
		shop.setShopName("测试的店铺");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setShopImg("test1");
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);
	}
	
	@Test
	public void testUpdatetShop() throws Exception {
		Shop shop = new Shop();
		shop.setShopId(29L);
		
		shop.setShopDesc("测试描述");
		shop.setShopAddr("测试地址");
		shop.setLastEditTime(new Date());

		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}



}
