package com.xqc.campusshop.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xqc.campusshop.BaseTest;
import com.xqc.campusshop.entity.Product;
import com.xqc.campusshop.entity.ProductCategory;
import com.xqc.campusshop.entity.Shop;

public class ProductServiceTest extends BaseTest{
	
	@Autowired
	private ProductService productService;
	
	@Test
	public void testModifyProduct(){
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(29L);
		ProductCategory pc =new ProductCategory();
		pc.setProductCategoryId(5L);
		product.setProductId(5L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("修改");
		product.setProductDesc("修改");
		
		productService.modifyProduct(product, null, null);
		
	}

}
