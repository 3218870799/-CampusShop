package com.xqc.campusshop.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 前台路由转发
 * 
 * @author A Cang（xqc）
 *
 */
@Controller
@RequestMapping("/frontend")
public class FrontendController {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	private String index() {
		return "frontend/index";
	}
}
