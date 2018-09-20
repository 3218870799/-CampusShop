package com.xqc.campusshop.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqc.campusshop.BaseTest;
import com.xqc.campusshop.entity.PersonInfo;
import com.xqc.campusshop.entity.Product;
import com.xqc.campusshop.entity.Shop;
import com.xqc.campusshop.entity.UserProductMap;

public class UserProductMapDaoTest extends BaseTest {
	@Autowired
	private UserProductMapDao userProductMapDao;

	@Test
	@Ignore
	public void testInsertUserProductMap() throws Exception {
		UserProductMap userProductMap = new UserProductMap();
		
		PersonInfo customer = new PersonInfo();
		customer.setUserId(12L);
		
		userProductMap.setUser(customer);
		
		userProductMap.setCreateTime(new Date());
		userProductMap.setPoint(5);
		
		Product product = new Product();
		product.setProductId(1L);
		userProductMap.setProduct(product);
		
		Shop shop = new Shop();
		shop.setShopId(29L);
		userProductMap.setShop(shop);
		
		userProductMap.setOperator(customer);

		int effectedNum = userProductMapDao
				.insertUserProductMap(userProductMap);
		System.out.println(effectedNum);
	}

	@Test
	public void testBQueryUserProductMapList() throws Exception {
		UserProductMap userProductMap = new UserProductMap();

		List<UserProductMap> userProductMapList = userProductMapDao
				.queryUserProductMapList(userProductMap, 0, 3);
		System.out.println(userProductMapList.size());
/*		int count = userProductMapDao.queryUserProductMapCount(userProductMap);
		System.out.println(count);*/
	}
}
