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


}
