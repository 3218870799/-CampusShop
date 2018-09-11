package com.xqc.campusshop.web.frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xqc.campusshop.entity.HeadLine;
import com.xqc.campusshop.entity.ShopCategory;
import com.xqc.campusshop.service.HeadLineService;
import com.xqc.campusshop.service.ShopCategoryService;

/**
 * 主页Controller
 * @author A Cang（xqc）
 *
 */
@Controller
@RequestMapping("/frontend")
public class MainPageController {
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private HeadLineService headLineService;

	/**
	 * 初始化前端展示系统的主页信息，包括获取一级店铺类别列表以及头条列表
	 * @return
	 */
	@RequestMapping(value = "/listmainpageinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> list1stShopCategory() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		try {
			shopCategoryList = shopCategoryService
					.getShopCategoryList(null);
			modelMap.put("shopCategoryList", shopCategoryList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		List<HeadLine> headLineList = new ArrayList<HeadLine>();
		try {
			HeadLine headLineCondition = new HeadLine();
			headLineCondition.setEnableStatus(1);
			headLineList = headLineService.getHeadLineList(headLineCondition);
			modelMap.put("headLineList", headLineList);
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg",e.getMessage());
			return modelMap;
		}
		modelMap.put("success", true);
	
		return modelMap;
	}

}
