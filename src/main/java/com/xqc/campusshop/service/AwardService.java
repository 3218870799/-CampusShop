package com.xqc.campusshop.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xqc.campusshop.dto.AwardExecution;
import com.xqc.campusshop.entity.Award;
/**
 * 奖品管理Service接口
 * 
 * @author A Cang（xqc）
 *
 */
public interface AwardService {

	/**
	 * 查询奖品列表
	 * @param awardCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	AwardExecution getAwardList(Award awardCondition, int pageIndex,
			int pageSize);

	/**
	 * 查询奖品
	 * @param awardId
	 * @return
	 */
	Award getAwardById(long awardId);

	/**
	 * 添加奖品
	 * @param award
	 * @param thumbnail
	 * @return
	 */
	AwardExecution addAward(Award award, CommonsMultipartFile thumbnail);

	/**
	 * 修改奖品
	 * @param award
	 * @param thumbnail
	 * @param awardImgs
	 * @return
	 */
	AwardExecution modifyAward(Award award, CommonsMultipartFile thumbnail);

}
