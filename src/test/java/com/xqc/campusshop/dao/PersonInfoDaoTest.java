package com.xqc.campusshop.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqc.campusshop.BaseTest;
import com.xqc.campusshop.entity.PersonInfo;

public class PersonInfoDaoTest extends BaseTest {
	
	@Autowired
	private PersonInfoDao personInfoDao;

	@Test
	@Ignore
	public void testInsertPersonInfo() throws Exception {
		PersonInfo personInfo = new PersonInfo();
		personInfo.setName("我爱你");
		personInfo.setGender("女");
		personInfo.setUserType(1);
		personInfo.setCreateTime(new Date());
		personInfo.setLastEditTime(new Date());
		personInfo.setEnableStatus(1);
		int effectedNum = personInfoDao.insertPersonInfo(personInfo);
		assertEquals(1, effectedNum);
	}
	
	@Test
	@Ignore
	public void testQueryPersonInfoById(){
		long userId = 13L;
		PersonInfo personInfo = personInfoDao.queryPersonInfoById(userId);
		System.out.println(personInfo.getName());
	}
	
	@Test
	@Ignore
	public void testQueryPersonInfoList() throws Exception {
		PersonInfo personInfo = new PersonInfo();
		List<PersonInfo> personInfoList = personInfoDao.queryPersonInfoList(
				personInfo, 0, 10);
		System.out.println(personInfoList.size());
		int count = personInfoDao.queryPersonInfoCount(personInfo);
		System.out.println(count);
		personInfo.setName("测试");
		personInfoList = personInfoDao.queryPersonInfoList(personInfo, 0, 2);
		System.out.println(personInfoList.size());
		count = personInfoDao.queryPersonInfoCount(personInfo);
		System.out.println(count);

	}

	@Test
	@Ignore
	public void testUpdatePersonInfo() {
		PersonInfo personInfo = new PersonInfo();
		long userId = 12L;
		personInfo.setUserId(userId);
		personInfo.setName("修改名称");
		personInfo.setGender("女");
		int effectedNum = personInfoDao.updatePersonInfo(personInfo);
		System.out.println(effectedNum);
	}

	@Test
	public void testDeletePersonInfoByName() throws Exception {
		int effectedNum = personInfoDao.deletePersonInfo(12L);
		System.out.println(effectedNum);

	}
	

}
