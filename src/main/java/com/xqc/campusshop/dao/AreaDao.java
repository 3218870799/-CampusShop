package com.xqc.campusshop.dao;

import java.util.List;

import com.xqc.campusshop.entity.Area;

public interface AreaDao {
	/**
	 * 列出地域列表
	 * 
	 * @param areaCondition
	 * @return
	 */
	List<Area> queryArea();
}
