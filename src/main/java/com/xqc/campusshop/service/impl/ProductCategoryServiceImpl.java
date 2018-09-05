package com.xqc.campusshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqc.campusshop.dao.ProductCategoryDao;
import com.xqc.campusshop.entity.ProductCategory;
import com.xqc.campusshop.service.ProductCategoryService;

/**
 * 商品类别实现类
 * 
 * @author A Cang（xqc）
 *
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		return productCategoryDao.queryByShopId(shopId);
	}

}
