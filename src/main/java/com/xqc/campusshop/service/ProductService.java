package com.xqc.campusshop.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xqc.campusshop.dto.ProductExecution;
import com.xqc.campusshop.entity.Product;

/**
 * 商品Service接口
 * @author A Cang（xqc）
 *
 */

public interface ProductService {
	

	/**
	 *添加商品信息以及图片处理
	 * @param product
	 * @param thumbnail
	 * @param productImgs
	 * @return
	 * @throws RuntimeException
	 */
	ProductExecution addProduct(Product product, CommonsMultipartFile thumbnail, List<CommonsMultipartFile> productImgs)
			throws RuntimeException;
	
	/**
	 * 分页 获取产品列表显示
	 * @param productCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);


}
