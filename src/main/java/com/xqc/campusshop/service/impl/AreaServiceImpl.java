package com.xqc.campusshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqc.campusshop.dao.AreaDao;
import com.xqc.campusshop.entity.Area;
import com.xqc.campusshop.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaDao areaDao;
	
	@Override
	public List<Area> getAreaList() {
		
		return areaDao.queryArea();
	}

}
