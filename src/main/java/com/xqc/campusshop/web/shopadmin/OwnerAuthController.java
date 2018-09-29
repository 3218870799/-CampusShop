package com.xqc.campusshop.web.shopadmin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xqc.campusshop.dto.LocalAuthExecution;
import com.xqc.campusshop.entity.LocalAuth;
import com.xqc.campusshop.enums.LocalAuthStateEnum;
import com.xqc.campusshop.service.LocalAuthService;
import com.xqc.campusshop.util.CodeUtil;
import com.xqc.campusshop.util.HttpServletRequestUtil;
import com.xqc.campusshop.util.MD5;

@Controller
@RequestMapping(value = "shopadmin", method = { RequestMethod.GET,
		RequestMethod.POST })
public class OwnerAuthController {
	@Autowired
	private LocalAuthService localAuthService;
	/**
	 * 验证店家登录
	 */
	@RequestMapping(value = "/ownerlogincheck", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> ownerLoginCheck(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//是否验证验证码
		boolean needVerify = HttpServletRequestUtil.getBoolean(request,
				"needVerify");
		if (needVerify && !CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		//获取登录名与密码
		String userName = HttpServletRequestUtil.getString(request, "userName");
		String password = HttpServletRequestUtil.getString(request, "password");
		if (userName != null && password != null) {
			password = MD5.getMd5(password);
			LocalAuth localAuth = localAuthService
					.getLocalAuthByUserNameAndPwd(userName, password);
			if (localAuth != null) {
				if (localAuth.getPersonInfo().getUserType()==2) {
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

	/**
	 * 用户注册本地账号
	 * @param request
	 * @return
	 */		
	@RequestMapping(value = "/ownerregister", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> ownerRegister(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//验证码
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		ObjectMapper mapper = new ObjectMapper();
		LocalAuth localAuth = null;
		//获取前台信息
		String localAuthStr = HttpServletRequestUtil.getString(request,
				"localAuthStr");
		MultipartHttpServletRequest multipartRequest = null;
		//图片处理
		CommonsMultipartFile profileImg = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			multipartRequest = (MultipartHttpServletRequest) request;
			profileImg = (CommonsMultipartFile) multipartRequest
					.getFile("thumbnail");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		//类型转换
		try {
			localAuth = mapper.readValue(localAuthStr, LocalAuth.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		if (localAuth != null && localAuth.getPassword() != null
				&& localAuth.getUserName() != null) {
			try {

				LocalAuthExecution le = localAuthService.register(localAuth);
				if (le.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", le.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入注册信息");
		}
		return modelMap;
	}


}
