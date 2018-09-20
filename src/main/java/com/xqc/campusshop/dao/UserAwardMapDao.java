package com.xqc.campusshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xqc.campusshop.entity.UserAwardMap;
/**
 * 顾客奖品映射数据访问接口
 * @author A Cang（xqc）
 *
 */
public interface UserAwardMapDao {
	/**
	 * 根据传入的查询条件分页返回用户兑换奖品记录的列表信息
	 * @param userAwardCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<UserAwardMap> queryUserAwardMapList(
			@Param("userAwardCondition") UserAwardMap userAwardCondition,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	/**
	 * 配合queryUserAwardMapList返回相同条件下兑换商品记录数
	 * @param userAwardCondition
	 * @return
	 */
	int queryUserAwardMapCount(
			@Param("userAwardCondition") UserAwardMap userAwardCondition);

	/**
	 * 根据userAwardId返回商品兑换信息
	 * @param userAwardId
	 * @return
	 */
	UserAwardMap queryUserAwardMapById(long userAwardId);

	/**
	 * 添加一条奖品兑换信息
	 * @param userAwardMap
	 * @return
	 */
	int insertUserAwardMap(UserAwardMap userAwardMap);

	/**
	 * 更新商品兑换信息，主要是领取状态
	 * @param userAwardMap
	 * @return
	 */
	int updateUserAwardMap(UserAwardMap userAwardMap);
}
