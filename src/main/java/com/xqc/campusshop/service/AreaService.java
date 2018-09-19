package com.xqc.campusshop.service;

import java.util.List;

import com.xqc.campusshop.dto.AreaExecution;
import com.xqc.campusshop.entity.Area;

public interface AreaService {
	/**
	 * 查询区域列表
	 * @return
	 */
	List<Area> getAreaList();
	/**
	 * 添加区域信息
	 * @param area
	 * @return
	 */
	AreaExecution addArea(Area area);

	/**
	 * 修改区域信息
	 * @param area
	 * @return
	 */
	AreaExecution modifyArea(Area area);

	/**
	 * 删除区域
	 * @param areaId
	 * @return
	 */
	AreaExecution removeArea(long areaId);

	/**
	 * 批量删除
	 * @param areaIdList
	 * @return
	 */
	AreaExecution removeAreaList(List<Long> areaIdList);

}
