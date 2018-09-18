package com.xqc.campusshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xqc.campusshop.entity.Award;

/**
 * 奖品数据访问层
 * @author A Cang（xqc）
 *
 */
public interface AwardDao {
	/**
	 * 查询奖品列表
	 * @param awardCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Award> queryAwardList(@Param("awardCondition") Award awardCondition,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	/**
	 * 查询总数
	 * @param awardCondition
	 * @return
	 */
	int queryAwardCount(@Param("awardCondition") Award awardCondition);

	/**
	 * 通过奖品Id查询
	 * 
	 * @param awardId
	 * @return
	 */
	Award queryAwardByAwardId(long awardId);

	/**
	 * 添加奖品
	 * @param award
	 * @return
	 */
	int insertAward(Award award);

	/**
	 * 修改奖品
	 * @param award
	 * @return
	 */
	int updateAward(Award award);
	/**
	 * 删除奖品
	 * @param award
	 * @return
	 */
	int deleteAward(Award award);
}
