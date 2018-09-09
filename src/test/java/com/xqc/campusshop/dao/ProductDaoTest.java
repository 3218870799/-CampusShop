package com.xqc.campusshop.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqc.campusshop.BaseTest;
import com.xqc.campusshop.entity.Product;
import com.xqc.campusshop.entity.ProductCategory;
import com.xqc.campusshop.entity.Shop;

public class ProductDaoTest extends BaseTest{
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	@Ignore
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
	
	@Test
	@Ignore
	public void testQueryProductList() throws Exception {
		Product product = new Product();
		List<Product> productList = productDao.queryProductList(product, 0, 3);
		assertEquals(3, productList.size());
	}
	
	@Test
	public void testUpdateProduct() throws Exception {
		Product product = new Product();
		ProductCategory pc =new ProductCategory();
		Shop shop = new Shop();
		product.setProductId(5L);
		shop.setShopId(29L);
		pc.setProductCategoryId(5L);
		
		product.setShop(shop);
		product.setProductCategory(pc);
		
		product.setProductName("第一个产品");
		productDao.updateProduct(product);

	}
	
	
	
	
	
	
	
	

}
