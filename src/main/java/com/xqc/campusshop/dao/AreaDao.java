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
	
	/**
	 * 添加区域
	 * @param area
	 * @return
	 */
	int insertArea(Area area);

	/**
	 * 修改区域信息
	 * @param area
	 * @return
	 */
	int updateArea(Area area);

	/**
	 * 删除区域信息
	 * @param areaId
	 * @return
	 */
	int deleteArea(long areaId);

	/**
	 * 批量删除区域信息
	 * @param areaIdList
	 * @return
	 */
	int batchDeleteArea(List<Long> areaIdList);
}
