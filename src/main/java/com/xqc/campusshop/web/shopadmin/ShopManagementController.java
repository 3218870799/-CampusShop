package com.xqc.campusshop.web.shopadmin;

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
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xqc.campusshop.dto.ShopExecution;
import com.xqc.campusshop.entity.Area;
import com.xqc.campusshop.entity.PersonInfo;
import com.xqc.campusshop.entity.Shop;
import com.xqc.campusshop.entity.ShopCategory;
import com.xqc.campusshop.enums.ShopStateEnum;
import com.xqc.campusshop.service.AreaService;
import com.xqc.campusshop.service.ShopCategoryService;
import com.xqc.campusshop.service.ShopService;
import com.xqc.campusshop.util.CodeUtil;
import com.xqc.campusshop.util.HttpServletRequestUtil;

@Controller
@RequestMapping(value="/shopadmin")
public class ShopManagementController {
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@Autowired
	private AreaService areaService;
	
	/**
	 * 店铺注册
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/registershop",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> registerShop(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		
		//验证验证
		if(!CodeUtil.checkVerifyCode(request)){
			modelMap.put("success",false);
			modelMap.put("errMsg","输入了错误的验证码");
			return modelMap;
		}
		
		//1。接受并转化相应的参数，包括店铺信息以及图片信息
		//获取约定好的shopStr
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		
		try{
			shop = mapper.readValue(shopStr, Shop.class);
		}catch(Exception e){
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg = null;
		//从本次会话中的上下文获取相关文件
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if(commonsMultipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		}else{
			modelMap.put("success", false);
			modelMap.put("errMsg","上传图片不能为空");
			return modelMap;
		}
		//2.注册店铺，尽量减少用户输入
		if(shop!= null&& shopImg!=null){
			//Session To do
			//user由登陆时传入
//			PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
			
			PersonInfo owner = new PersonInfo();
			owner.setUserId(12L);

			shop.setOwner(owner);
			ShopExecution se = shopService.addShop(shop, shopImg);
			if(se.getState() == ShopStateEnum.CHECK.getState()){
				modelMap.put("success", true);
				//如果店铺创建成功，将店铺保存在Session中,取出该用户可以操作的店铺列表
/*				@SuppressWarnings("unchecked")
				List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
				if(shopList == null||shopList.size()==0){
					shopList = new ArrayList<Shop>();
				}
				shopList.add(se.getShop());
				request.getSession().setAttribute("shopList", shopList);*/
			}else{
				modelMap.put("success", false);
				modelMap.put("errMsg",se.getStateInfo());
				
			}
			
			
			
		}else{
			modelMap.put("success", false);
			modelMap.put("errMsg","请输入店铺信息");
			return modelMap;
		}
		return modelMap;
		
	}
	
	/**
	 * 获取店铺初始信息
	 * @return
	 */
	@RequestMapping(value="/getshopinitinfo",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getShopIninInfo(){
		
		Map<String,Object> modelMap = new HashMap<String,Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		List<Area> areaList = new ArrayList<Area>();
		
		try{
			shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
			areaList = areaService.getAreaList();
			
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		}catch(Exception e){
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
				
		return modelMap;
		
	}
	
	/**
	 * 由Id获取店铺信息
	 * id由HttpServletRequestUtil进行处理
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//获取shopId
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		//前端已经传入
		if (shopId != null && shopId > -1) {
			try {
				//获取Shop
				Shop shop = shopService.getByShopId(shopId);
				//获取区域列表
				List<Area> areaList = areaService.getAreaList();
				
				modelMap.put("shop", shop);
				modelMap.put("areaList", areaList);
				modelMap.put("success", true);
				
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty shopId");
		}
		return modelMap;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	

}
