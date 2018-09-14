package com.xqc.campusshop.service;

import com.xqc.campusshop.entity.PersonInfo;

/**
 * 用户信息Service接口
 * @author A Cang（xqc）
 *
 */
public interface PersonInfoService {

	/**
	 * 根据用户id获取personInfo信息
	 * @param userId
	 * @return
	 */
	PersonInfo getPersonInfoById(Long userId);
}
