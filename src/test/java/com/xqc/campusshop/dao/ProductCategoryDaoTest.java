package com.xqc.campusshop.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqc.campusshop.BaseTest;
import com.xqc.campusshop.entity.ProductCategory;

public class ProductCategoryDaoTest extends BaseTest{

	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Test
	@Ignore
	public void testQueryByShopId(){
		long shopId = 29;
		List<ProductCategory> productCategoryList = productCategoryDao.queryByShopId(shopId);
		
		System.out.println(productCategoryList.toString());
		
	}
	
	@Test
	@Ignore
	public void testInsertProductCategory() throws Exception {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryName("商品类别1");
		productCategory.setPriority(1);
		productCategory.setCreateTime(new Date());
		productCategory.setShopId(29L);
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("商品类别2");
		productCategory2.setPriority(2);
		productCategory2.setCreateTime(new Date());
		productCategory2.setShopId(29L);
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productCategory);
		productCategoryList.add(productCategory2);
		int effectedNum = productCategoryDao
				.batchInsertProductCategory(productCategoryList);
		assertEquals(2, effectedNum);
	}
	
	@Test
	public void testDeleteProductCategory(){
		long shopId = 29;
		List<ProductCategory> productCategoryList = productCategoryDao
				.queryByShopId(shopId);
		int effectedNum = productCategoryDao.deleteProductCategory(
				productCategoryList.get(0).getProductCategoryId(), shopId);
		assertEquals(1, effectedNum);
	}
		
	
}
