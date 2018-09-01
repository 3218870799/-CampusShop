package com.xqc.campusshop.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xqc.campusshop.dao.ShopDao;
import com.xqc.campusshop.dto.ShopExecution;
import com.xqc.campusshop.entity.Shop;
import com.xqc.campusshop.enums.ShopStateEnum;
import com.xqc.campusshop.exceptions.ShopOperationException;
import com.xqc.campusshop.service.ShopService;
import com.xqc.campusshop.util.FileUtil;
import com.xqc.campusshop.util.ImageUtil;


/**
 * 店铺Service实现
 * @author A Cang（xqc）
 *
 */
@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;

	@Override
	@Transactional
	/**
	 * 使用注解控制事务方法的优点： 1.开发团队达成一致约定，明确标注事务方法的编程风格
	 * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
	 * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
	 */
	public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg)
			throws ShopOperationException {
		//空值判断
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			//给店铺信息赋初始值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			
			//添加店铺信息
			int effectedNum = shopDao.insertShop(shop);
			
			if (effectedNum <= 0) {
				throw new ShopOperationException("店铺创建失败");
			} else {
					if (shopImg != null) {
						//存储图片
						try{
							addShopImg(shop,shopImg);
						}catch (Exception e) {
							throw new ShopOperationException("add ShopImg Error"+ e.getMessage());
						}
						//更新店铺图片地址
						effectedNum = shopDao.updateShop(shop);
						if (effectedNum <= 0) {
							throw new ShopOperationException("创建图片地址失败");
						}
					}
				}
		} catch (Exception e) {
			throw new ShopOperationException("addShop error: " + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}

	private void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
		String dest = FileUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
		shop.setShopImg(shopImgAddr);
	}


}
