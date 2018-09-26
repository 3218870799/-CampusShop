package com.xqc.campusshop.service;

import java.util.Date;
import java.util.List;

import com.xqc.campusshop.entity.ProductSellDaily;

public interface ProductSellDailyService {
	/**
	 * 每天定时对所有店铺的商品销量进行统计
	 */
	void dailyCalculate();
	
	/**
	 * 根据查询条件返回销量信息
	 * @param productSellDailyCondition
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	List<ProductSellDaily> listProductDaily(ProductSellDaily productSellDailyCondition,Date beginTime,Date endTime);

}
