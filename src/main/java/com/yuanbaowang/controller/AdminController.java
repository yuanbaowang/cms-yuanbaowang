/**
 * 
 */
package com.yuanbaowang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.yuanbaowang.bean.Article;
import com.yuanbaowang.common.CmsError;
import com.yuanbaowang.common.CmsMessage;
import com.yuanbaowang.service.ArticleService;

/**
 * @author 袁保旺
 *
 * 2019年12月16日 下午8:23:58 
 */
@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private ArticleService service;
	
	@RequestMapping("index")
	public String index() {
		return "admin/index";
	}
	
	/**
	 * 	管理员列表
	 */
	@RequestMapping("article")
	public String article(HttpServletRequest request,@RequestParam(defaultValue = "-1")int status,@RequestParam(defaultValue = "1")int pageNum) {
		PageInfo<Article> list = service.list(status,pageNum);
		request.getSession().setAttribute("list", list);
		request.getSession().setAttribute("status", status);
		return "admin/article/list";
	}
	
	/**
	 * 	设置状态
	 */
	@RequestMapping("setArticleHot")
	@ResponseBody
	public CmsMessage setArticleHot(int id,int hot) {
		if(hot != 0 && hot != 1) {
				return new CmsMessage(CmsError.UPDATE_FALSE,"参数不合法！",null);
			}
			if(id < 0) {
				return new CmsMessage(CmsError.UPDATE_FALSE,"ID参数不合法！",null);
			}
			Article article = service.getSimpleById(id);
			
			if(article.getHot() == hot) {
				return new CmsMessage(CmsError.NO_UPDATE,"数据无需更改！",null);
			}
			//设置热门
			int result = service.setHot(id,hot);
			if(result !=0) {
				return new CmsMessage(CmsError.UPDATE_SUCCESS,"",null);
			}
			return new CmsMessage(CmsError.UPDATE_FALSE,"设置失败！",null);
	}
	
	/**
	 * 	设置热门
	 */
	@RequestMapping("setArticleStatus")
	@ResponseBody
	public CmsMessage setArticleStatus(int id,int status) {
		if(status != 1 && status != 2) {
			return new CmsMessage(CmsError.UPDATE_FALSE,"参数不合法！",null);
		}
		if(id < 0) {
			return new CmsMessage(CmsError.UPDATE_FALSE,"ID参数不合法！",null);
		}
		Article article = service.getSimpleById(id);
		
		if(article.getStatus() == status) {
			return new CmsMessage(CmsError.NO_UPDATE,"数据无需更改！",null);
		}
		//设置热门
		int result = service.setStatus(id,status);
		if(result !=0) {
			return new CmsMessage(CmsError.UPDATE_SUCCESS,"",null);
		}
		return new CmsMessage(CmsError.UPDATE_FALSE,"设置失败！",null);
		
	}
	
}
