package com.xqc.campusshop.dao;

import java.util.List;

import com.xqc.campusshop.entity.ProductImg;

/**
 * 商品图片数据访问层
 * @author A Cang（xqc）
 *
 */
public interface ProductImgDao {

	/**
	 * 批量添加商品详情图片
	 * @param productImgList
	 * @return
	 */
	int batchInsertProductImg(List<ProductImg> productImgList);

	/**
	 * 根据商品Id查询商品图片
	 * @param productId
	 * @return
	 */
	List<ProductImg> queryProductImgList(long productId);
	/**
	 * 删除商品图片
	 * @param productId
	 * @return
	 */
	int deleteProductImgByProductId(long productId);
}
