package com.xqc.campusshop.service;

import com.xqc.campusshop.dto.LocalAuthExecution;
import com.xqc.campusshop.entity.LocalAuth;

/**
 * 本地账号管理Service接口
 * @author A Cang（xqc）
 *
 */
public interface LocalAuthService {
	/**
	 * 通过账号密码获取平台账号信息，登陆使用
	 * 
	 * @param userName
	 * @return
	 */
	LocalAuth getLocalAuthByUserNameAndPwd(String userName, String password);
	
	/**
	 * 生成本平台账号
	 * @param localAuth
	 * @return
	 * @throws RuntimeException
	 */
	LocalAuthExecution register(LocalAuth localAuth) throws RuntimeException;
	
	/**
	 * 通过UserId获取用户信息
	 * @param userId
	 * @return
	 */
	LocalAuth getLocalAuthByUserId(long userId);

	/**
	 * 修改本地账号信息
	 * @param localAuthId
	 * @param userName
	 * @param password
	 * @param newPassword
	 * @param lastEditTime
	 * @return
	 */
	LocalAuthExecution modifyLocalAuth(Long userId, String userName,
			String password, String newPassword);
	

	

}
