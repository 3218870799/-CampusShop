package com.xqc.campusshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xqc.campusshop.entity.UserShopMap;

/**
 * 顾客店铺积分实体类
 * @author A Cang（xqc）
 *
 */
public interface UserShopMapDao {
	/**
	 * 分页查询店铺下用户积分列表
	 * @param userShopCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<UserShopMap> queryUserShopMapList(
			@Param("userShopCondition") UserShopMap userShopCondition,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	UserShopMap queryUserShopMap(@Param("userId") long userId,
			@Param("shopId") long shopId);

	/**
	 * 配合查询总数
	 * @param userShopCondition
	 * @return
	 */
	int queryUserShopMapCount(
			@Param("userShopCondition") UserShopMap userShopCondition);

	/**
	 * 插入用户店铺积分数据
	 * @param userShopMap
	 * @return
	 */
	int insertUserShopMap(UserShopMap userShopMap);

	/**
	 * 更新积分
	 * @param userShopMap
	 * @return
	 */
	int updateUserShopMapPoint(UserShopMap userShopMap);

}
