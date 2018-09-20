package com.xqc.campusshop.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqc.campusshop.BaseTest;
import com.xqc.campusshop.entity.Award;
import com.xqc.campusshop.entity.PersonInfo;
import com.xqc.campusshop.entity.Shop;
import com.xqc.campusshop.entity.UserAwardMap;


public class UserAwardMapDaoTest extends BaseTest {
	@Autowired
	private UserAwardMapDao userAwardMapDao;

	@Test
	@Ignore
	public void testAInsertUserAwardMap() throws Exception {
		
		UserAwardMap userAwardMap = new UserAwardMap();
		
		PersonInfo custormer = new PersonInfo();
		custormer.setUserId(12L);
		
		userAwardMap.setUser(custormer);
		userAwardMap.setOperator(custormer);
		
		Award award = new Award();
		award.setAwardId(1L);
		
		userAwardMap.setAward(award);
		
		Shop shop = new Shop();
		
		shop.setShopId(29L);
		
		userAwardMap.setShop(shop);

		userAwardMap.setCreateTime(new Date());
		userAwardMap.setUsedStatus(1);
		int effectedNum = userAwardMapDao.insertUserAwardMap(userAwardMap);
		System.out.println(effectedNum);
	}

	@Test
	@Ignore
	public void testQueryUserAwardMapList() throws Exception {

		
		UserAwardMap userAwardMap = new UserAwardMap();
		
		List<UserAwardMap> userAwardMapList = userAwardMapDao
				.queryUserAwardMapList(userAwardMap, 0, 3);
		System.out.println(userAwardMapList.size());
		
		int count = userAwardMapDao.queryUserAwardMapCount(userAwardMap);
		System.out.println(count);
		
	}
	
	@Test
	public void testUpdateUserAwardMap(){
		
		UserAwardMap userAwardMap = new UserAwardMap();
		PersonInfo customer = new PersonInfo();
		
		customer.setUserId(13L);
		userAwardMap.setUser(customer);
		userAwardMap.setUserAwardId(1L);
		userAwardMap.setUsedStatus(0);
		userAwardMap.setPoint(2);
		userAwardMapDao.updateUserAwardMap(userAwardMap);
		
		
	}
	
	
	
}
