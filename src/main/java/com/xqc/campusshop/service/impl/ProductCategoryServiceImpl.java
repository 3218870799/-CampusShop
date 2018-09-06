package com.xqc.campusshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xqc.campusshop.dao.ProductCategoryDao;
import com.xqc.campusshop.dto.ProductCategoryExecution;
import com.xqc.campusshop.entity.ProductCategory;
import com.xqc.campusshop.enums.ProductCategoryStateEnum;
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
	
	/**
	 *获取产品类别列表
	 */
	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		return productCategoryDao.queryByShopId(shopId);
	}

	/**
	 * @Transactional声明事务
	 * 批量添加方法
	 */
	@Override
	@Transactional
	public ProductCategoryExecution batchAddProductCategory(
			List<ProductCategory> productCategoryList) throws RuntimeException {
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				int effectedNum = productCategoryDao
						.batchInsertProductCategory(productCategoryList);
				if (effectedNum <= 0) {
					throw new RuntimeException("店铺类别失败");
				} else {

					return new ProductCategoryExecution(
							ProductCategoryStateEnum.SUCCESS);
				}

			} catch (Exception e) {
				throw new RuntimeException("batchAddProductCategory error: "
						+ e.getMessage());
			}
		} else {
			return new ProductCategoryExecution(
					ProductCategoryStateEnum.INNER_ERROR);
		}

	}

}
