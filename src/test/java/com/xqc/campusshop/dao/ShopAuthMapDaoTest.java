package com.xqc.campusshop.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqc.campusshop.BaseTest;
import com.xqc.campusshop.entity.PersonInfo;
import com.xqc.campusshop.entity.Shop;
import com.xqc.campusshop.entity.ShopAuthMap;

public class ShopAuthMapDaoTest extends BaseTest {
	
	@Autowired
	private ShopAuthMapDao shopAuthMapDao;

	@Test
	@Ignore
	public void testInsertShopAuthMap() throws Exception {
		ShopAuthMap shopAuthMap = new ShopAuthMap();
		shopAuthMap.setTitle("CEO");
		shopAuthMap.setTitleFlag(1);
		shopAuthMap.setCreateTime(new Date());
		shopAuthMap.setLastEditTime(new Date());
		shopAuthMap.setEnableStatus(0);
		
		PersonInfo employee = new PersonInfo();
		employee.setUserId(13L);
		shopAuthMap.setEmployee(employee);
		
		Shop shop = new Shop();
		shop.setShopId(29L);
		shopAuthMap.setShop(shop);
		
		int effectedNum = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
		System.out.println(effectedNum);
	}

	@Test
	@Ignore
	public void testQueryShopAuthMapListByShopId() throws Exception {
       List<ShopAuthMap> shopAuthMapList = shopAuthMapDao.queryShopAuthMapListByShopId(29, 0, 2);
       System.out.println(shopAuthMapList.size());
       int count = shopAuthMapDao.queryShopAuthCountByShopId(29);
       assertEquals(2, count);
       
	}

	@Test
	@Ignore
	public void testUpdateShopAuthMap() throws Exception {
		ShopAuthMap shopAuthMap = new ShopAuthMap();
		shopAuthMap.setShopAuthId(28L);
		shopAuthMap.setTitle("CCO");
		shopAuthMap.setTitleFlag(2);
		shopAuthMap.setLastEditTime(new Date());
		Shop shop = new Shop();
		shop.setShopId(29L);
		shopAuthMap.setShop(shop);
		
		int effectedNum = shopAuthMapDao.updateShopAuthMap(shopAuthMap);
		assertEquals(1, effectedNum);
	}

	@Test
	public void testDeleteShopAuthMap() throws Exception {
		long employeeId = 28;
		long shopId = 29;
		int effectedNum = shopAuthMapDao.deleteShopAuthMap(employeeId, shopId);
		assertEquals(1, effectedNum);
	}
}
