package com.xqc.campusshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xqc.campusshop.dao.ProductCategoryDao;
import com.xqc.campusshop.dao.ProductDao;
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
	
	@Autowired
	private ProductDao productDao;
	
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

	/**
	 * 删除商品类别，如果商品类别下有商品，则清空商品再进行删除
	 */
	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws RuntimeException {
		//如果商品类别下有商品，则将商品清空再删除
		//解除关联进行删除
		try {
			int effectedNum = productDao
					.updateProductCategoryToNull(productCategoryId);
			if (effectedNum < 0) {
				throw new RuntimeException("商品类别更新失败");
			}
		} catch (Exception e) {
			throw new RuntimeException("deleteProductCategory error: "
					+ e.getMessage());
		}
		try {
			int effectedNum = productCategoryDao.deleteProductCategory(
					productCategoryId, shopId);
			if (effectedNum <= 0) {
				throw new RuntimeException("店铺类别删除失败");
			} else {
				return new ProductCategoryExecution(
						ProductCategoryStateEnum.SUCCESS);
			}

		} catch (Exception e) {
			throw new RuntimeException("deleteProductCategory error: "
					+ e.getMessage());
		}
		
	}

	/**
	 * 查询指定店铺商品
	 */
	@Override
	public List<ProductCategory> getByShopId(long shopId) {
		return productCategoryDao.queryByShopId(shopId);
	}

}
