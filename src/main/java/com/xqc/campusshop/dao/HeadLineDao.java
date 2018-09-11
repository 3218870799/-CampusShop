package com.xqc.campusshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xqc.campusshop.entity.HeadLine;

/**
 * 头条数据访问层接口
 * @author A Cang（xqc）
 *
 */
public interface HeadLineDao {

	/**
	 * 根据传入的查询条件查询
	 * 
	 * @return
	 */
	List<HeadLine> queryHeadLine(
			@Param("headLineCondition") HeadLine headLineCondition);

	/**
	 * 根据头条Id查询头条信息
	 * @param lineId
	 * @return
	 */
	HeadLine queryHeadLineById(long lineId);

	/**
	 * 查询多个头条信息
	 * @param lineIdList
	 * @return
	 */
	List<HeadLine> queryHeadLineByIds(List<Long> lineIdList);

	/**
	 * 添加头条
	 * 
	 * @param headLine
	 * @return
	 */
	int insertHeadLine(HeadLine headLine);

	/**
	 * 
	 * 修改头条
	 * @param headLine
	 * @return
	 */
	int updateHeadLine(HeadLine headLine);

	/**
	 * 
	 * 删除头条
	 * @param headLineId
	 * @return
	 */
	int deleteHeadLine(long headLineId);

	/**
	 * 批量删除头条
	 * @param lineIdList
	 * @return
	 */
	int batchDeleteHeadLine(List<Long> lineIdList);
}
