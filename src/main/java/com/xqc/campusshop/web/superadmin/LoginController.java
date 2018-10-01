package com.xqc.campusshop.web.superadmin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xqc.campusshop.entity.LocalAuth;
import com.xqc.campusshop.service.LocalAuthService;
import com.xqc.campusshop.util.HttpServletRequestUtil;
import com.xqc.campusshop.util.MD5;
/**
 * 管理员本地账号登陆
 * 
 * @author A Cang（xqc）
 *
 */

@Controller
@RequestMapping("/superadmin")
public class LoginController {
	@Autowired
	private LocalAuthService localAuthService;

	/**
	 * 登陆检验
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logincheck", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> loginCheck(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//获取名字与密码
		String userName = HttpServletRequestUtil.getString(request, "userName");
		String password = HttpServletRequestUtil.getString(request, "password");
		//控制判断
		if (userName != null && password != null) {
			//将密码进行md5加密
			password = MD5.getMd5(password);
			//从本地获取账号与密码
			LocalAuth localAuth = localAuthService
					.getLocalAuthByUserNameAndPwd(userName, password);
			if (localAuth != null) {
				if (localAuth.getPersonInfo().getUserType()==3){
					modelMap.put("success", true);
					request.getSession().setAttribute("user",
							localAuth.getPersonInfo());
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", "非管理员没有权限访问");
				}
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户名或密码错误");
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户名和密码均不能为空");
		}
		return modelMap;
	}
}
