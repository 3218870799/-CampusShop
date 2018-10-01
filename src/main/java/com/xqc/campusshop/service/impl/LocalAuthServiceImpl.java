package com.xqc.campusshop.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xqc.campusshop.dao.LocalAuthDao;
import com.xqc.campusshop.dao.PersonInfoDao;
import com.xqc.campusshop.dto.LocalAuthExecution;
import com.xqc.campusshop.entity.LocalAuth;
import com.xqc.campusshop.entity.PersonInfo;
import com.xqc.campusshop.enums.LocalAuthStateEnum;
import com.xqc.campusshop.service.LocalAuthService;
import com.xqc.campusshop.util.MD5;

/**
 * 本地账号管理Service实现
 * @author A Cang（xqc）
 *
 */
@Service
public class LocalAuthServiceImpl implements LocalAuthService {

	@Autowired
	private LocalAuthDao localAuthDao;
	@Autowired
	private PersonInfoDao personInfoDao;

	/**
	 * 通过账号密码获取平台账号信息，登陆使用
	 */
	@Override
	public LocalAuth getLocalAuthByUserNameAndPwd(String userName,
			String password) {
		return localAuthDao.queryLocalByUserNameAndPwd(userName, password);
	}

	/**
	 * 生成本平台账号信息
	 */
	@Override
	@Transactional
	public LocalAuthExecution register(LocalAuth localAuth) throws RuntimeException {
		
		if (localAuth == null || localAuth.getPassword() == null
				|| localAuth.getUserName() == null) {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
		try {
			localAuth.setCreateTime(new Date());
			localAuth.setLastEditTime(new Date());
			localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
			
			if (localAuth.getPersonInfo() != null) {
				try {
					PersonInfo personInfo = localAuth.getPersonInfo();
					personInfo.setCreateTime(new Date());
					personInfo.setUserType(2);
					personInfo.setEnableStatus(1);
					
					personInfo.setLastEditTime(new Date());
					int effectedNum = personInfoDao
							.insertPersonInfo(personInfo);
					localAuth.setUserId(personInfo.getUserId());
					if (effectedNum <= 0) {
						throw new RuntimeException("添加用户信息失败");
					}
				} catch (Exception e) {
					throw new RuntimeException("insertPersonInfo error: "
							+ e.getMessage());
				}
			}
			int effectedNum = localAuthDao.insertLocalAuth(localAuth);
			if (effectedNum <= 0) {
				throw new RuntimeException("帐号创建失败");
			} else {
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS,
						localAuth);
			}
		} catch (Exception e) {
			throw new RuntimeException("insertLocalAuth error: "
					+ e.getMessage());
		}
	}
	/**
	 * 修改账号密码
	 */
	@Override
	@Transactional
	public LocalAuthExecution modifyLocalAuth(Long userId, String userName,
			String password, String newPassword) {
		if (userId != null && userName != null && password != null
				&& newPassword != null && !password.equals(newPassword)) {
			try {
				int effectedNum = localAuthDao.updateLocalAuth(userId,
						userName, MD5.getMd5(password),
						MD5.getMd5(newPassword), new Date());
				if (effectedNum <= 0) {
					throw new RuntimeException("更新密码失败");
				}
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
			} catch (Exception e) {
				throw new RuntimeException("更新密码失败:" + e.toString());
			}
		} else {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
	}
	
	
	@Override
	public LocalAuth getLocalAuthByUserId(long userId) {
		return localAuthDao.queryLocalByUserId(userId);
	}



	

}
