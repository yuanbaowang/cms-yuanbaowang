/**
 * 
 */
package com.yuanbaowang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanbaowang.bean.Article;
import com.yuanbaowang.common.CmsError;
import com.yuanbaowang.common.CmsMessage;
import com.yuanbaowang.service.ArticleService;

/**
 * @author 袁保旺
 *
 * 2019年12月17日 下午2:39:19 
 */
@Controller
@RequestMapping("article")
public class ArticleController {
	
	@Autowired
	private ArticleService service;
	
	
	@RequestMapping("getDetail")
	@ResponseBody
	public CmsMessage getDetail(int id) {
		if(id <= 0) {
			
		}
		//获取文章详情
		Article article = service.getById(id);
		if(article == null) {
			return new CmsMessage(CmsError.NO_EXIST,"文章不存在",null);
		}
		return new CmsMessage(CmsError.SUCCESS,"",article);
	}
}
