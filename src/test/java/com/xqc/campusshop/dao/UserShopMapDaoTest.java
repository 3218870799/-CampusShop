package com.xqc.campusshop.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqc.campusshop.BaseTest;
import com.xqc.campusshop.entity.PersonInfo;
import com.xqc.campusshop.entity.Shop;
import com.xqc.campusshop.entity.UserShopMap;

public class UserShopMapDaoTest extends BaseTest{
	
	@Autowired
	private UserShopMapDao userShopMapDao;
	
	@Test
	@Ignore
	public void testInsertUserShopMap(){
		UserShopMap userShopMap = new UserShopMap();
		userShopMap.setCreateTime(new Date());
		userShopMap.setPoint(50);
		PersonInfo user = new PersonInfo();
		user.setUserId(12L);
		userShopMap.setUser(user);
		Shop shop  = new Shop();
		shop.setShopId(29L);
		userShopMap.setShop(shop);
		userShopMapDao.insertUserShopMap(userShopMap);
		
	}
	
	@Test
	@Ignore
	public void testQueryUserShopMapList(){
		UserShopMap userShopMap = new UserShopMap();
		userShopMapDao.queryUserShopMapList(userShopMap, 0, 2);
		System.out.println(userShopMapDao.queryUserShopMapCount(userShopMap));
		userShopMap = userShopMapDao.queryUserShopMap(12L, 29L);
		System.out.println(userShopMap.getPoint());
		
		
	}
	
	@Test
	public void testUpdate(){
		UserShopMap userShopMap = new UserShopMap();
		PersonInfo user = new PersonInfo();
		user.setUserId(12L);
		userShopMap.setUser(user);
		Shop shop  = new Shop();
		shop.setShopId(29L);
		userShopMap.setShop(shop);
		
		userShopMap.setPoint(5);
		
		userShopMapDao.updateUserShopMapPoint(userShopMap);
		
	}
	
	
}
