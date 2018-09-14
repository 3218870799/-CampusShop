package com.xqc.campusshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqc.campusshop.dao.PersonInfoDao;
import com.xqc.campusshop.entity.PersonInfo;
import com.xqc.campusshop.service.PersonInfoService;

/**
 * 用户信息实现
 * @author A Cang（xqc）
 *
 */
@Service
public class PersonInfoServiceImpl implements PersonInfoService {
	@Autowired
	private PersonInfoDao personInfoDao;

	@Override
	public PersonInfo getPersonInfoById(Long userId) {
		return personInfoDao.queryPersonInfoById(userId);
	}
}
