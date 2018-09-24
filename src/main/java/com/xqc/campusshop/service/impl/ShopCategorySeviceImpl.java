package com.xqc.campusshop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xqc.campusshop.dao.ShopCategoryDao;
import com.xqc.campusshop.dto.ShopCategoryExecution;
import com.xqc.campusshop.entity.ShopCategory;
import com.xqc.campusshop.enums.ShopCategoryStateEnum;
import com.xqc.campusshop.service.ShopCategoryService;
import com.xqc.campusshop.util.FileUtil;
import com.xqc.campusshop.util.ImageUtil;

/**
 * 店铺类别管理实现
 * @author A Cang（xqc）
 *
 */
@Service
public class ShopCategorySeviceImpl implements ShopCategoryService{
	
	@Autowired
	ShopCategoryDao shopCategoryDao;
	

	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
		return shopCategoryDao.queryShopCategory(shopCategoryCondition);
	}

	@Override
	public ShopCategory getShopCategoryById(Long shopCategoryId) {
		return shopCategoryDao.queryShopCategoryById(shopCategoryId);
	}

	@Override
	public List<ShopCategory> getFirstLevelShopCategoryList() {
		 ShopCategory shopCategory = new ShopCategory();
		 shopCategory.setParentId(null);
		return  shopCategoryDao.queryShopCategory(shopCategory);
	}

	@Override
	@Transactional
	public ShopCategoryExecution addShopCategory(ShopCategory shopCategory, CommonsMultipartFile thumbnail) {
		if (shopCategory != null) {
			shopCategory.setCreateTime(new Date());
			shopCategory.setLastEditTime(new Date());
			if (thumbnail != null) {
				addThumbnail(shopCategory, thumbnail);
			}
			try {
				int effectedNum = shopCategoryDao
						.insertShopCategory(shopCategory);
				if(effectedNum>0){
					return new ShopCategoryExecution(ShopCategoryStateEnum.SUCCESS,shopCategory);
				}else{
					return new ShopCategoryExecution(ShopCategoryStateEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				throw new RuntimeException("添加店铺类别信息失败:" + e.toString());
			}
		} else {
			return new ShopCategoryExecution(ShopCategoryStateEnum.EMPTY);
		}
	}

	@Override
	@Transactional
	public ShopCategoryExecution modifyShopCategory(ShopCategory shopCategory, CommonsMultipartFile thumbnail,
			boolean thumbnailChange) {
		if (shopCategory.getShopCategoryId() != null
				&& shopCategory.getShopCategoryId() > 0) {
			shopCategory.setLastEditTime(new Date());
			if (thumbnail != null && thumbnailChange == true) {
				ShopCategory tempShopCategory = shopCategoryDao
						.queryShopCategoryById(shopCategory.getShopCategoryId());
				if (tempShopCategory.getShopCategoryImg() != null) {
					FileUtil.deleteFile(tempShopCategory.getShopCategoryImg());
				}
				addThumbnail(shopCategory, thumbnail);
			}
			try {
				int effectedNum = shopCategoryDao
						.updateShopCategory(shopCategory);
				if (effectedNum > 0) {
					return new ShopCategoryExecution(
							ShopCategoryStateEnum.SUCCESS, shopCategory);
				} else {
					return new ShopCategoryExecution(
							ShopCategoryStateEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				throw new RuntimeException("更新店铺类别信息失败:" + e.toString());
			}
		} else {
			return new ShopCategoryExecution(ShopCategoryStateEnum.EMPTY);
		}
	}

	@Override
	@Transactional
	public ShopCategoryExecution removeShopCategory(Long shopCategoryId) {
		if (shopCategoryId > 0) {
				ShopCategory tempShopCategory = shopCategoryDao
						.queryShopCategoryById(shopCategoryId);
				if (tempShopCategory.getShopCategoryImg() != null) {
					FileUtil.deleteFile(tempShopCategory.getShopCategoryImg());
				}
				int effectedNum = shopCategoryDao
						.deleteShopCategory(shopCategoryId);
				if (effectedNum > 0) {
					return new ShopCategoryExecution(
							ShopCategoryStateEnum.SUCCESS);
				} else {
					return new ShopCategoryExecution(
							ShopCategoryStateEnum.INNER_ERROR);
				}
		} else {
			return new ShopCategoryExecution(ShopCategoryStateEnum.EMPTY);
		}
	}

	@Override
	@Transactional
	public ShopCategoryExecution removeShopCategoryList(List<Long> shopCategoryIdList) {
		if (shopCategoryIdList != null && shopCategoryIdList.size() > 0) {
			try {
				List<ShopCategory> shopCategoryList = shopCategoryDao
						.queryShopCategoryByIds(shopCategoryIdList);
				for (ShopCategory shopCategory : shopCategoryList) {
					if (shopCategory.getShopCategoryImg() != null) {
						FileUtil.deleteFile(shopCategory.getShopCategoryImg());
					}
				}
				int effectedNum = shopCategoryDao
						.batchDeleteShopCategory(shopCategoryIdList);
				if (effectedNum > 0) {
					return new ShopCategoryExecution(
							ShopCategoryStateEnum.SUCCESS);
				} else {
					return new ShopCategoryExecution(
							ShopCategoryStateEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				throw new RuntimeException("删除店铺类别信息失败:" + e.toString());
			}
		} else {
			return new ShopCategoryExecution(ShopCategoryStateEnum.EMPTY);
		}
	}	
	
	private void addThumbnail(ShopCategory shopCategory, CommonsMultipartFile thumbnail) {

		String dest = FileUtil.getShopCategoryImagePath();
		String thumbnailAddr = ImageUtil.generateNormalImg(thumbnail, dest);
		shopCategory.setShopCategoryImg(thumbnailAddr);
		
		
	}

}
