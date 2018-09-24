package com.xqc.campusshop.service;

import com.xqc.campusshop.dto.PersonInfoExecution;
import com.xqc.campusshop.entity.PersonInfo;

/**
 * 用户信息Service接口
 * @author A Cang（xqc）
 *
 */
public interface PersonInfoService {

	/**
	 * 根据id查询个人信息
	 * @param userId
	 * @return
	 */
	PersonInfo getPersonInfoById(Long userId);

	/**
	 * 分页查询个人信息
	 * @param personInfoCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PersonInfoExecution getPersonInfoList(PersonInfo personInfoCondition,
			int pageIndex, int pageSize);

	/**
	 * 添加个人信息
	 * @param personInfo
	 * @return
	 */
	PersonInfoExecution addPersonInfo(PersonInfo personInfo);

	/**
	 * 修改个人信息
	 * @param personInfo
	 * @return
	 */
	PersonInfoExecution modifyPersonInfo(PersonInfo personInfo);
}
