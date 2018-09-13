package com.xqc.campusshop.dao;

import com.xqc.campusshop.entity.PersonInfo;

public interface PersonInfoDao {

	/**
	 * 通过Id查询用户
	 * @param userId
	 * @return
	 */
	PersonInfo queryPersonInfoById(long userId);

	/**
	 * 添加用户信息
	 * @param wechatAuth
	 * @return
	 */
	int insertPersonInfo(PersonInfo personInfo);
}
