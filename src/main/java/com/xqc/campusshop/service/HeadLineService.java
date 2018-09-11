package com.xqc.campusshop.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xqc.campusshop.dto.HeadLineExecution;
import com.xqc.campusshop.entity.HeadLine;

/**
 * 头条的service层
 * @author A Cang（xqc）
 *
 */
public interface HeadLineService {

	/**
	 * 查询头条列表
	 * @param headLineCondition
	 * @return
	 * @throws IOException
	 */
	List<HeadLine> getHeadLineList(HeadLine headLineCondition)
			throws IOException;

	/**
	 * 添加头条
	 * @param headLine
	 * @param thumbnail
	 * @return
	 */
	HeadLineExecution addHeadLine(HeadLine headLine,
			CommonsMultipartFile thumbnail);

	/**
	 * 修改头条信息
	 * @param headLine
	 * @param thumbnail
	 * @param thumbnailChange
	 * @return
	 */
	HeadLineExecution modifyHeadLine(HeadLine headLine,
			CommonsMultipartFile thumbnail);

	/**
	 * 删除头条信息
	 * @param headLineId
	 * @return
	 */
	HeadLineExecution removeHeadLine(long headLineId);

	/**
	 * 删除多个头条信息
	 * @param headLineIdList
	 * @return
	 */
	HeadLineExecution removeHeadLineList(List<Long> headLineIdList);

}
