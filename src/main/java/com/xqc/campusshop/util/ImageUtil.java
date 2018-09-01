package com.xqc.campusshop.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;

public class ImageUtil {
	
	public static String generateThumbnail(CommonsMultipartFile thumbnail, String targetAddr) {
		String realFileName = FileUtil.getRandomFileName();
		String extension = getFileExtension(thumbnail);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getInputStream()).size(200, 200).outputQuality(0.8f).toFile(dest);
		} catch (IOException e) {
			throw new RuntimeException("创建缩略图失败：" + e.toString());
		}
		return relativeAddr;
	}
	/**
	 * 生成随机文件名，当前年月日时分秒+五位随机数
	 * @return
	 */
/*	public static String getRandomFileName() {
		// 生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
		int rannum = r.nextInt(89999) + 10000; // 获取随机数
		String nowTimeStr = sDateFormat.format(new Date()); // 当前时间
		return nowTimeStr + rannum;
	}*/
	
	/**
	 * 获取输入文件流的扩展名
	 * @param thumbnail
	 * @return
	 */
	private static String getFileExtension(CommonsMultipartFile cFile) {
		String originalFileName = cFile.getOriginalFilename();
		return originalFileName.substring(originalFileName.lastIndexOf("."));
	}
	
	/**
	 * 创建目标路径上所涉及到的目录
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = FileUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}
}
