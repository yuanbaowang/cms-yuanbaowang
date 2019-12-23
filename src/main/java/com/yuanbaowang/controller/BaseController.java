/**
 * 
 */
package com.yuanbaowang.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import yuanbaowang_cms_utils.FileUtils;

/**
 * @author 袁保旺
 *
 * 2019年12月22日 下午9:23:25 
 */
public class BaseController {
	
	@Value("${upload.path}")
	String picRootPath;
	
	@Value("${pic.path}")
	String picUrl;
	
	/**
	 * 	进行处理上传文件
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	protected String processFile(MultipartFile file) throws IllegalStateException, IOException {
		
		if(file.isEmpty()) {
			return "";
		}
		
		//获取当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String subPath = sdf.format(new Date());
		//图片存放的路径
		File path = new File(picRootPath+"/"+subPath);
		//判断目标目录时间是否存在
		if(!path.exists()) {
			//路径不存在创建
			path.mkdirs();
		}
		//计算文件的扩展名
		String suffixName = FileUtils.getSuffixName(file.getOriginalFilename());
		//使用UUid生成随机文件名
		String fileName = UUID.randomUUID().toString()+suffixName;
		//把文件另存到新的文件夹中
		file.transferTo(new File(picRootPath+"/"+subPath+"/"+fileName));
		return subPath+"/"+fileName;
	}

}
