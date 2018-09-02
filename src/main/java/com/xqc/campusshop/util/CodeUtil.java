package com.xqc.campusshop.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码验证工具类
 * @author A Cang（xqc）
 *
 */
public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request) {
		//生成的验证码
		String verifyCodeExpected = (String) request.getSession().getAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//实际输入的验证码
		String verifyCodeActual = HttpServletRequestUtil.getString(request,
				"verifyCodeActual");
		
		if (verifyCodeActual == null
				|| !verifyCodeActual.equalsIgnoreCase(verifyCodeExpected)) {
			return false;
		}
		return true;
	}
}
