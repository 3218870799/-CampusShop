package com.xqc.campusshop.util;

/**
 * 路径处理
 * @author A Cang（xqc）
 *
 */
public class PathUtil {
	
	private static String  seperator = System.getProperty("file.separator");
	/**
	 * 返回项目图片的根路径
	 * @return
	 */
	public static String getImgBasePath(){
		String os = System.getProperty("os.name");
		String basePath = "";
		if(os.toLowerCase().startsWith("win")){
			basePath = "D:/projectdev/image/";
		}else{
			basePath = "/home/proCampusshop/image/";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}
	
	/**
	 * 根据不同需求返回项目图片子路径
	 * @param shopId
	 * @return
	 */
	public static String getShopImagePath(long shopId){
		String imagePath = "/upload/item/shop/" + shopId + "/";
		return imagePath.replace("/", seperator);
	}

}
