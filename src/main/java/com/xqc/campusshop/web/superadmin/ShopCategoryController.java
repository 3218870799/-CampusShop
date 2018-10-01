package com.xqc.campusshop.web.superadmin;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xqc.campusshop.dto.Result;
import com.xqc.campusshop.dto.ShopCategoryExecution;
import com.xqc.campusshop.entity.ConstantForSuperAdmin;
import com.xqc.campusshop.entity.ShopCategory;
import com.xqc.campusshop.enums.ShopCategoryStateEnum;
import com.xqc.campusshop.service.ShopCategoryService;
import com.xqc.campusshop.util.HttpServletRequestUtil;
/**
 * 店铺类别管理Controller
 * 
 * @author A Cang（xqc）
 *
 */
@Controller
@RequestMapping("/superadmin")
public class ShopCategoryController {
	@Autowired
	private ShopCategoryService shopCategoryService;

	/**
	 * 列出店铺类别列表
	 * @return
	 */
	@RequestMapping(value = "/listshopcategorys", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> listShopCategorys() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> list = new ArrayList<ShopCategory>();
		try {
			list = shopCategoryService.getShopCategoryList(null);
			modelMap.put(ConstantForSuperAdmin.PAGE_SIZE, list);
			modelMap.put(ConstantForSuperAdmin.TOTAL, list.size());
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}

	/**
	 * 列出一级列表
	 * @return
	 */
	@RequestMapping(value = "/list1stlevelshopcategorys", method = RequestMethod.POST)
	@ResponseBody
	private Result<List<ShopCategory>> list1stLevelShopCategorys() {
		List<ShopCategory> list = new ArrayList<ShopCategory>();
		try {
			
			list = shopCategoryService.getFirstLevelShopCategoryList();
		} catch (Exception e) {
			e.printStackTrace();
			ShopCategoryStateEnum a = ShopCategoryStateEnum.INNER_ERROR;
			return new Result<List<ShopCategory>>(false, a.getState(),
					a.getStateInfo());
		}
		return new Result<List<ShopCategory>>(true, list);
	}

	/**
	 * 添加店铺类别
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addshopcategory", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addShopCategory(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		ShopCategory shopCategory = null;
		//获取前端传过来的shopCategory串
		String shopCategoryStr = HttpServletRequestUtil.getString(request,
				"shopCategoryStr");
		//获取图片处理请求
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		CommonsMultipartFile thumbnail = (CommonsMultipartFile) multipartRequest
				.getFile("shopCategoryManagementAdd_shopCategoryImg");
		try {
			shopCategory = mapper
					.readValue(shopCategoryStr, ShopCategory.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		
		if (shopCategory != null && thumbnail != null) {
			try {
				// decode可能有中文的地方
				shopCategory.setShopCategoryName((shopCategory
						.getShopCategoryName() == null) ? null : (URLDecoder
						.decode(shopCategory.getShopCategoryName(), "UTF-8")));
				shopCategory.setShopCategoryDesc((shopCategory
						.getShopCategoryDesc() == null) ? null : (URLDecoder
						.decode(shopCategory.getShopCategoryDesc(), "UTF-8")));
				ShopCategoryExecution ae = shopCategoryService.addShopCategory(
						shopCategory, thumbnail);
				if (ae.getState() == ShopCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", ae.getStateInfo());
				}
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺类别信息");
		}
		return modelMap;
	}

	/**
	 * 修改店铺类别信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modifyshopcategory", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyShopCategory(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		ShopCategory shopCategory = null;
		//获取前端传的shopCategoryStr
		String shopCategoryStr = HttpServletRequestUtil.getString(request,
				"shopCategoryStr");
		//获取前端传的图片流
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		//转换
		CommonsMultipartFile thumbnail = (CommonsMultipartFile) multipartRequest
				.getFile("shopCategoryManagementEdit_shopCategoryImg");
		try {
			shopCategory = mapper
					.readValue(shopCategoryStr, ShopCategory.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		//执行判断
		if (shopCategory != null && shopCategory.getShopCategoryId() != null) {
			try {
				boolean thumbnailChange = HttpServletRequestUtil.getBoolean(
						request, "thumbnailChange");
				// decode可能有中文的地方
				shopCategory.setShopCategoryName((shopCategory
						.getShopCategoryName() == null) ? null : (URLDecoder
						.decode(shopCategory.getShopCategoryName(), "UTF-8")));
				shopCategory.setShopCategoryDesc((shopCategory
						.getShopCategoryDesc() == null) ? null : (URLDecoder
						.decode(shopCategory.getShopCategoryDesc(), "UTF-8")));
				ShopCategoryExecution ae = shopCategoryService
						.modifyShopCategory(shopCategory, thumbnail,
								thumbnailChange);
				if (ae.getState() == ShopCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", ae.getStateInfo());
				}
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺类别信息");
		}
		return modelMap;
	}

	/**
	 * 删除店铺类别
	 * @param shopCategoryId
	 * @return
	 */
	@RequestMapping(value = "/removeshopcategory", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> removeShopCategory(Long shopCategoryId) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (shopCategoryId != null && shopCategoryId > 0) {
			try {
				ShopCategoryExecution ae = shopCategoryService
						.removeShopCategory(shopCategoryId);
				if (ae.getState() == ShopCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", ae.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请选择店铺类别");
		}
		return modelMap;
	}

	/**
	 * 批量删除店铺类别
	 * @param shopCategoryIdListStr
	 * @return
	 */
	@RequestMapping(value = "/removeshopcategories", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> removeShopCategories(
			String shopCategoryIdListStr) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(
				ArrayList.class, Long.class);
		List<Long> shopCategoryIdList = null;
		try {
			shopCategoryIdList = mapper.readValue(shopCategoryIdListStr,
					javaType);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		if (shopCategoryIdList != null && shopCategoryIdList.size() > 0) {
			try {
				ShopCategoryExecution ae = shopCategoryService
						.removeShopCategoryList(shopCategoryIdList);
				if (ae.getState() == ShopCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", ae.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺类别信息");
		}
		return modelMap;
	}
}
