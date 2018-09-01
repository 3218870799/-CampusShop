package com.xqc.campusshop.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 路由转发
 * @author A Cang（xqc）
 *
 */
@Controller
@RequestMapping(value="shopadmin",method={RequestMethod.GET})
public class ShopAdminController {
	
	@RequestMapping(value="/shopoperation")
	public String shopOperation(){
		return "shop/shopoperation";
	}
	

}