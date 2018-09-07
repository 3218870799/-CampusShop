package com.xqc.campusshop.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqc.campusshop.BaseTest;
import com.xqc.campusshop.entity.Product;
import com.xqc.campusshop.entity.ProductCategory;
import com.xqc.campusshop.entity.Shop;

public class ProductDaoTest extends BaseTest{
	
	@Autowired
	private ProductDao productDao;
	
	@Test
	public void testInsertProduct() throws Exception {
		Shop shop1 = new Shop();
		shop1.setShopId(29L);

		ProductCategory pc1 = new ProductCategory();
		pc1.setProductCategoryId(2L);
		
		Product product1 = new Product();
		product1.setProductName("测试3");
		product1.setProductDesc("测试Desc3");
		product1.setImgAddr("test3");
		product1.setPriority(0);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setShop(shop1);
		product1.setProductCategory(pc1);

		productDao.insertProduct(product1);

	}

}
