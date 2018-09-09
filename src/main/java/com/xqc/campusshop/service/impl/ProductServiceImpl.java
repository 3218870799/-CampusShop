package com.xqc.campusshop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xqc.campusshop.dao.ProductDao;
import com.xqc.campusshop.dao.ProductImgDao;
import com.xqc.campusshop.dto.ProductExecution;
import com.xqc.campusshop.entity.Product;
import com.xqc.campusshop.entity.ProductImg;
import com.xqc.campusshop.enums.ProductStateEnum;
import com.xqc.campusshop.service.ProductService;
import com.xqc.campusshop.util.FileUtil;
import com.xqc.campusshop.util.ImageUtil;
import com.xqc.campusshop.util.PageCalculator;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	/**
	 * 1:处理缩略图，获取缩略图路径并赋值给product
	 * 2：往tb_product写入商品信息，获取ProductId
	 * 3：结合productId批量处理商品详情图
	 * 4：将商品详情图列表批量插入tb_product_img中
	 */

	@Override
	@Transactional
	public ProductExecution addProduct(Product product, CommonsMultipartFile thumbnail,
			List<CommonsMultipartFile> productImgs) throws RuntimeException {
		//空值判断
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			//给商品实体类设置默认属性
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			product.setEnableStatus(1);
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new RuntimeException("创建商品失败");
				}
			} catch (Exception e) {
				throw new RuntimeException("创建商品失败:" + e.toString());
			}
			if (productImgs != null && productImgs.size() > 0) {
				addProductImgs(product, productImgs);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	/**
	 * 批量处理商品图片
	 * @param product
	 * @param productImgs
	 */
	private void addProductImgs(Product product, List<CommonsMultipartFile> productImgs) {
		String dest = FileUtil.getShopImagePath(product.getShop().getShopId());
		List<String> imgAddrList = ImageUtil.generateNormalImgs(productImgs, dest);
		if (imgAddrList != null && imgAddrList.size() > 0) {
			List<ProductImg> productImgList = new ArrayList<ProductImg>();
			for (String imgAddr : imgAddrList) {
				ProductImg productImg = new ProductImg();
				productImg.setImgAddr(imgAddr);
				productImg.setProductId(product.getProductId());
				productImg.setCreateTime(new Date());
				productImgList.add(productImg);
			}
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if (effectedNum <= 0) {
					throw new RuntimeException("创建商品详情图片失败");
				}
			} catch (Exception e) {
				throw new RuntimeException("创建商品详情图片失败:" + e.toString());
			}
		}
	}

	private void addThumbnail(Product product, CommonsMultipartFile thumbnail) {
		String dest = FileUtil.getShopImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);
	}

	/** 
	 * 分页获得商品列表，显示到前台
	 */
	@Override
	public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
		int count = productDao.queryProductCount(productCondition);
		ProductExecution pe = new ProductExecution();
		pe.setProductList(productList);
		pe.setCount(count);
		return pe;
	}

	@Override
	public Product getProductById(Long productId) {
		return productDao.queryProductByProductId(productId);
	}

	/**
	 * 更新商品信息
	 * 若缩略图有值，则处理缩略图
	 * 若原先存在缩略图先删除再添加新图，之后获取缩略图相对路径并赋值给product
	 * 若商品详情图列表有值，对商品详情图片列表进行同样的操作
	 * 将tb_product_img下的商品原本的图片详情图记录全部清除
	 * 更新tb_produc的信息
	 */
	@Override
	@Transactional
	public ProductExecution modifyProduct(Product product, CommonsMultipartFile thumbnail,
			List<CommonsMultipartFile> productImgs) throws RuntimeException {
		//空值判断
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			//给商品设置上默认属性
			product.setLastEditTime(new Date());
			
			
			
			
			//若商品缩略图不为空且原有缩略图不为空则删除原有缩略图并添加
			if (thumbnail != null) {
				//先获取一遍原有信息，因为原有信息里有图片地址
				Product tempProduct = productDao.queryProductByProductId(product.getProductId());
				if (tempProduct.getImgAddr() != null) {
					FileUtil.deleteFile(tempProduct.getImgAddr());
				}
				addThumbnail(product, thumbnail);
			}
			//如果有新存入的商品详情图，则将原本的先删除，并添加新的图片
			if (productImgs!= null && productImgs.size() > 0) {
				deleteProductImgs(product.getProductId());
				addProductImgs(product, productImgs);
			}
			
			
			
			try {
				int effectedNum = productDao.updateProduct(product);
				
				if (effectedNum <= 0) {
					throw new RuntimeException("更新商品信息失败");
				}
				
				return new ProductExecution(ProductStateEnum.SUCCESS, product);
			} catch (Exception e) {
				throw new RuntimeException("更新商品信息失败:" + e.toString());
			}
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}
	
	private void deleteProductImgs(long productId) {
		List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
		for (ProductImg productImg : productImgList) {
			FileUtil.deleteFile(productImg.getImgAddr());
		}
		productImgDao.deleteProductImgByProductId(productId);
	}
	
}
