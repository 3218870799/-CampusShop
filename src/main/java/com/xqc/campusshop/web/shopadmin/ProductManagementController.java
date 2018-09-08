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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xqc.campusshop.dto.ProductExecution;
import com.xqc.campusshop.entity.Product;
import com.xqc.campusshop.entity.ProductCategory;
import com.xqc.campusshop.entity.Shop;
import com.xqc.campusshop.enums.ProductStateEnum;
import com.xqc.campusshop.service.ProductCategoryService;
import com.xqc.campusshop.service.ProductService;
import com.xqc.campusshop.util.CodeUtil;
import com.xqc.campusshop.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/shopadmin")
public class ProductManagementController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductCategoryService productCategoryService;

	private static final int IMAGEMAXCOUNT = 6;

	@RequestMapping(value = "/listproductsbyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listProductsByShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		Shop currentShop = (Shop) request.getSession().getAttribute(
				"currentShop");
		if ((pageIndex > -1) && (pageSize > -1) && (currentShop != null)
				&& (currentShop.getShopId() != null)) {
			long productCategoryId = HttpServletRequestUtil.getLong(request,
					"productCategoryId");
			String productName = HttpServletRequestUtil.getString(request,
					"productName");
			Product productCondition = compactProductCondition4Search(
					currentShop.getShopId(), productCategoryId, productName);
			ProductExecution pe = productService.getProductList(
					productCondition, pageIndex, pageSize);
			modelMap.put("productList", pe.getProductList());
			modelMap.put("count", pe.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
		}
		return modelMap;
	}

	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addProduct(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		ObjectMapper mapper = new ObjectMapper();
		Product product = null;
		String productStr = HttpServletRequestUtil.getString(request,
				"productStr");
		MultipartHttpServletRequest multipartRequest = null;
		CommonsMultipartFile thumbnail = null;
		List<CommonsMultipartFile> productImgs = new ArrayList<CommonsMultipartFile>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		try {
			if (multipartResolver.isMultipart(request)) {
				multipartRequest = (MultipartHttpServletRequest) request;
				thumbnail = (CommonsMultipartFile) multipartRequest
						.getFile("thumbnail");
				for (int i = 0; i < IMAGEMAXCOUNT; i++) {
					CommonsMultipartFile productImg = (CommonsMultipartFile) multipartRequest
							.getFile("productImg" + i);
					if (productImg != null) {
						productImgs.add(productImg);
					}
				}
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "上传图片不能为空");
				return modelMap;
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		try {
			//转化为product实例
			product = mapper.readValue(productStr, Product.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		if (product != null && thumbnail != null && productImgs.size() > 0) {
			try {
				Shop currentShop = (Shop) request.getSession().getAttribute(
						"currentShop");
				Shop shop = new Shop();
				shop.setShopId(currentShop.getShopId());
				product.setShop(shop);
				ProductExecution pe = productService.addProduct(product,
						thumbnail, productImgs);
				if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入商品信息");
		}
		return modelMap;
	}
	
	
	private Product compactProductCondition4Search(long shopId,
			long productCategoryId, String productName) {
		Product productCondition = new Product();
		Shop shop = new Shop();
		shop.setShopId(shopId);
		productCondition.setShop(shop);
		if (productCategoryId != -1L) {
			ProductCategory productCategory = new ProductCategory();
			productCategory.setProductCategoryId(productCategoryId);
			productCondition.setProductCategory(productCategory);
		}
		if (productName != null) {
			productCondition.setProductName(productName);
		}
		return productCondition;
	}
	
	@RequestMapping(value = "/getproductbyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getProductById(@RequestParam Long productId) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (productId > -1) {
			Product product = productService.getProductById(productId);
			List<ProductCategory> productCategoryList = productCategoryService
					.getByShopId(product.getShop().getShopId());
			modelMap.put("product", product);
			modelMap.put("productCategoryList", productCategoryList);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
		}
		return modelMap;
	}
	
	
	
	

}
