package com.xqc.campusshop.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqc.campusshop.BaseTest;
import com.xqc.campusshop.entity.Area;


public class AreaDaoTest extends BaseTest {
	@Autowired
	private AreaDao areaDao;

	@Test
	@Ignore
	public void testInsertArea(){
		Area area = new Area();
		area.setAreaName("区域2");
		area.setPriority(1);
		area.setCreateTime(new Date());
		area.setLastEditTime(new Date());
		int effectedNum = areaDao.insertArea(area);
		System.out.println(effectedNum);
		
		
	}
	
	@Test
	@Ignore
	public void testQueryArea() throws Exception {
		List<Area> areaList = areaDao.queryArea();
		System.out.println(areaList.size());
	}
	
	@Test
	@Ignore
	public void testCUpdateArea() throws Exception {
		Area area = new Area();
		area.setAreaId(3);
		area.setAreaName("南苑");
		area.setLastEditTime(new Date());
		int effectedNum = areaDao.updateArea(area);
		System.out.println(effectedNum);
	}
	
	@Test
	public void testDeleteArea() throws Exception {
		
		areaDao.deleteArea(3);
		
		List<Integer> areaIdList = new ArrayList<Integer>();
		areaIdList.add(4);
		areaIdList.add(5);
		int effectedNum = areaDao.batchDeleteArea(areaIdList);
		System.out.println(effectedNum);
	}
	
	
	
}
