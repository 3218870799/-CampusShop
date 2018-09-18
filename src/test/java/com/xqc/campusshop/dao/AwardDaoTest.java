package com.xqc.campusshop.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqc.campusshop.BaseTest;
import com.xqc.campusshop.entity.Award;

public class AwardDaoTest extends BaseTest {

	@Autowired
	private AwardDao awardDao;

	@Test
	@Ignore
	public void testInsertAward() throws Exception {
		long shopId = 29L;
		Award award = new Award();
		award.setAwardName("奖品一");
		award.setAwardImg("test1");
		award.setPoint(5);
		award.setPriority(1);
		award.setEnableStatus(1);
		award.setCreateTime(new Date());
		award.setLastEditTime(new Date());
		award.setExpireTime(new Date());
		award.setShopId(shopId);
		int effectedNum = awardDao.insertAward(award);
		assertEquals(1, effectedNum);
	}

	@Test
	@Ignore
	public void testQueryAwardList() throws Exception {
		Award award = new Award();
		List<Award> awardList = awardDao.queryAwardList(award, 0, 3);
		System.out.println(awardList.size());
		int count = awardDao.queryAwardCount(award);
		System.out.println(count);
		award.setAwardName("测试");
		awardList = awardDao.queryAwardList(award, 0, 3);
		System.out.println(awardList.size());
		count = awardDao.queryAwardCount(award);
		System.out.println(count);
	}

	@Test
	@Ignore
	public void testQueryAwardByAwardId() throws Exception {
		awardDao.queryAwardByAwardId(1);
	}

	@Test
	@Ignore
	public void testUpdateAward() throws Exception {
		Award award = new Award();
		award.setAwardId(1L);
		award.setShopId(29L);
		award.setAwardName("第一个奖品");
		int effectedNum = awardDao.updateAward(award);
		System.out.println(effectedNum);
	}

	@Test
	public void testDeleteAward() throws Exception {
		Award award = new Award();
		award.setAwardId(2L);
		award.setShopId(29L);
		int effectedNum = awardDao.deleteAward(award);
		System.out.println(effectedNum);
	}
}
