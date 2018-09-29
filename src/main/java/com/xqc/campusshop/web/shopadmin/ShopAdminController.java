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
	
	@RequestMapping(value = "/shoplist", method = RequestMethod.GET)
	public String myList() {
		return "shop/shoplist";
	}
	@RequestMapping(value = "/shopmanagement")
	private String shopManageMent() {
		return "shop/shopmanage";

	}
	@RequestMapping(value = "/changepsw", method = RequestMethod.GET)
	private String changePsw() {
		return "shop/changepsw";
	}
	
	@RequestMapping(value = "/productcategorymanage", method = RequestMethod.GET)
	private String productCategoryManage(){
		return "shop/productcategorymanage";
	}
	
	@RequestMapping(value = "/productmanage", method = RequestMethod.GET)
	private String productManage() {
		return "shop/productmanage";
	}
	@RequestMapping(value = "/productedit", method = RequestMethod.GET)
	private String productEdit() {
		return "shop/productedit";
	}
	@RequestMapping(value = "/awardmanage", method = RequestMethod.GET)
	private String awardManage() {
		return "shop/awardmanage";
	}
	@RequestMapping(value = "/awardedit", method = RequestMethod.GET)
	private String awardEdit() {
		return "shop/awardedit";
	}
	@RequestMapping(value = "/shopauthmanagementpage", method = RequestMethod.GET)
	private String shopAuthManagement() {
		return "shop/shopauthmanage";
	}
	@RequestMapping(value = "/shopauthedit", method = RequestMethod.GET)
	private String shopAuthEdit() {
		return "shop/shopauthedit";
	}
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	private String register() {
		return "shop/register";
	}

	
	@RequestMapping(value = "/ownerbind", method = RequestMethod.GET)
	private String ownerBind() {
		return "shop/ownerbind";
	}

	@RequestMapping(value = "/shopedit", method = RequestMethod.GET)
	private String shopEdit() {
		return "shop/shopedit";
	}
	@RequestMapping(value = "/productbuycheck", method = RequestMethod.GET)
	private String productBuyCheck() {
		return "shop/productbuycheck";
	}

	@RequestMapping(value = "/awarddelivercheck", method = RequestMethod.GET)
	private String awardDeliverCheck() {
		return "shop/awarddelivercheck";
	}

	@RequestMapping(value = "/usershopcheck", method = RequestMethod.GET)
	private String userShopCheck() {
		return "shop/usershopcheck";
	}
	@RequestMapping(value = "/customermanage", method = RequestMethod.GET)
	private String customerManage() {
		return "shop/customermanage";
	}
	@RequestMapping(value = "/loginshoppage",method = RequestMethod.GET)
	private String loginShop(){
		return "shop/loginshop";
	}
	
	
	
}
