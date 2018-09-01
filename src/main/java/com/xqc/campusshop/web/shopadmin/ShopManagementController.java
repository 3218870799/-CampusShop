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
import com.xqc.campusshop.dto.ShopExecution;
import com.xqc.campusshop.entity.PersonInfo;
import com.xqc.campusshop.entity.Shop;
import com.xqc.campusshop.enums.ShopStateEnum;
import com.xqc.campusshop.service.ShopService;
import com.xqc.campusshop.util.HttpServletRequestUtil;

@Controller
@RequestMapping(value="/shopadmin")
public class ShopManagementController {
	
	@Autowired
	private ShopService shopService;
	
	@RequestMapping(value="/registershop",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> registerShop(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		
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
			PersonInfo owner = new PersonInfo();
			
			//Session To do
			owner.setUserId(12L);
			
			//
			
			 
			shop.setOwner(owner);
			ShopExecution se = shopService.addShop(shop, shopImg);
			if(se.getState() == ShopStateEnum.CHECK.getState()){
				modelMap.put("success", true);
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

}
