package com.xqc.campusshop.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.xqc.campusshop.entity.LocalAuth;

/**
 * 本地账号数据访问层
 * @author A Cang（xqc）
 *
 */
public interface LocalAuthDao {
	
	/**
	 * 通过账号和密码查询对应信息，登录用
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	LocalAuth queryLocalByUserNameAndPwd(@Param("userName") String userName,
			@Param("password") String password);

	/**
	 * 通过用户Id查询对应localAuth
	 * 
	 * @param userId
	 * @return
	 */
	LocalAuth queryLocalByUserId(@Param("userId") long userId);

	/**
	 * 添加平台账号
	 * 
	 * @param localAuth
	 * @return
	 */
	int insertLocalAuth(LocalAuth localAuth);

	/**
	 * 修改密码
	 * 
	 * @param localAuth
	 * @return
	 */
	int updateLocalAuth(@Param("userId") Long userId,
			@Param("userName") String userName,
			@Param("password") String password,
			@Param("newPassword") String newPassword,
			@Param("lastEditTime") Date lastEditTime);
	
	

}
