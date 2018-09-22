package com.xqc.campusshop.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * 验证码验证工具类
 * @author A Cang（xqc）
 *
 */
public class CodeUtil {
	/**
	 * 检验验证码是否与预期相同
	 * @param request
	 * @return
	 */
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
	/**
	 * 生成二维码
	 * 
	 * @param url
	 * @param resp
	 * @return bit的矩阵
	 */
	public static BitMatrix generateQRCodeStream(String url,
			HttpServletResponse resp) {
		//给相应添加头部信息，主要是告诉浏览器返回的是图片流
		resp.setHeader("Cache-Control", "no-store");
		resp.setHeader("Pragma", "no-cache");
		resp.setDateHeader("Expires", 0);
		resp.setContentType("image/png");
		//设置图片的文字编码以及内部边距
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 0);
		BitMatrix bitMatrix;
		try {
			//参数顺序分别为：编码内容，编码类型，生成图片宽度，高度，设置参数
			bitMatrix = new MultiFormatWriter().encode(url,
					BarcodeFormat.QR_CODE, 300, 300, hints);
		} catch (WriterException e) {
			e.printStackTrace();
			return null;
		}
		return bitMatrix;
	}
	
	
	
	
	
	
	
	
	
	
}
