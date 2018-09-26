package com.xqc.campusshop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqc.campusshop.dao.ProductSellDailyDao;
import com.xqc.campusshop.entity.ProductSellDaily;
import com.xqc.campusshop.service.ProductSellDailyService;

@Service
public class ProductSellDailyServiceImpl implements ProductSellDailyService{

	@Autowired
	private  ProductSellDailyDao productSellDailyDao; 
	
	@Override
	public void dailyCalculate() {
		//统计在tb_user_product_map里面产生销量的每个店铺的各种商品的日销量
		productSellDailyDao.insertProductSellDaily();
		System.out.println("执行每天统计销量的方法");
		//统计余下商品的日销量，全部置为0
		productSellDailyDao.insertDefaultProductSellDaily();
		
	}
	
	@Override
	public List<ProductSellDaily> listProductDaily(ProductSellDaily productSellDailyCondition,Date beginTime,Date endTime) {
		return productSellDailyDao.queryProductSellDailyList(productSellDailyCondition, beginTime, endTime);
	}

}
