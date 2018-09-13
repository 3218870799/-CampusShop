package com.xqc.campusshop.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqc.campusshop.BaseTest;
import com.xqc.campusshop.entity.PersonInfo;
import com.xqc.campusshop.entity.WechatAuth;


public class WechatAuthDaoTest extends BaseTest {
	@Autowired
	private WechatAuthDao wechatAuthDao;

	@Test
	@Ignore
	public void testInsertWechatAuth() throws Exception {
		
		WechatAuth wechatAuth = new WechatAuth();
		
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(13L);
		
		wechatAuth.setUserId(13L);
		wechatAuth.setPersonInfo(personInfo);
		wechatAuth.setOpenId("gfdfgahgfdhhjsye");
		wechatAuth.setCreateTime(new Date());
		int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
		assertEquals(1, effectedNum);
	}

	@Test
	public void testQueryWechatAuthByOpenId() throws Exception {
		WechatAuth wechatAuth = wechatAuthDao
				.queryWechatInfoByOpenId("gfdfgahgfdhhjsye");
		assertEquals("我爱你", wechatAuth.getPersonInfo().getName());
	}
}
