package com.xqc.campusshop.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xqc.campusshop.dao.HeadLineDao;
import com.xqc.campusshop.dto.HeadLineExecution;
import com.xqc.campusshop.entity.HeadLine;
import com.xqc.campusshop.service.HeadLineService;

/**
 * 头条操作实现类
 * @author A Cang（xqc）
 *
 */
@Service
public class HeadLineServiceImpl implements HeadLineService {
	
	@Autowired
	private HeadLineDao headLineDao;
	
	/**
	 * 查询头条列表
	 */
	@Override
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
		return headLineDao.queryHeadLine(headLineCondition);
	}

	@Override
	public HeadLineExecution addHeadLine(HeadLine headLine, CommonsMultipartFile thumbnail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HeadLineExecution modifyHeadLine(HeadLine headLine, CommonsMultipartFile thumbnail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HeadLineExecution removeHeadLine(long headLineId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HeadLineExecution removeHeadLineList(List<Long> headLineIdList) {
		// TODO Auto-generated method stub
		return null;
	}



}
