/**
 * 
 */
package com.yuanbaowang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.yuanbaowang.bean.Article;
import com.yuanbaowang.bean.Comment;
import com.yuanbaowang.bean.User;
import com.yuanbaowang.common.CmsContant;
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
	
	
	/**
	 *	点击	根据id 查看文章
	 */
	@RequestMapping("detail")
	public String detail(HttpServletRequest request,int id) {
		Article byId = service.getById(id);
		request.getSession().setAttribute("article", byId);
		request.getSession().setAttribute("id", id);
		return "detail";
	}
	
	/**
	 * 	添加评论
	 */
	@RequestMapping("addComment")
	@ResponseBody
	public CmsMessage addComment(HttpServletRequest request, int articleId,String content) {
		System.out.println("243"+articleId+","+content);
		//获取当前登录用户
		User user = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		if(user == null) {
			return new CmsMessage(CmsContant.NOT_LOGIN,"您尚未登录，不可发表评论",null);
		}
		Comment com = new Comment();
		com.setUserId(user.getId());
		com.setArticleId(articleId);
		com.setContent(content);
		int i = service.addComment(com);
		if(i > 0 ) {
			return new CmsMessage(CmsError.SUCCESS,"成功",null);
		}
			return new CmsMessage(CmsError.NO_EXIST,"发布失败，请于",null);
	}
	
	/**
	 * 	所有评论
	 */
	@RequestMapping("comments")
	public String comments(HttpServletRequest request,int id,int pageNum) {
		PageInfo<Comment> list = service.getComments(id,pageNum);
		request.getSession().setAttribute("list", list);
		request.getSession().setAttribute("id", id);
		return "comment";
	}
	
	
}
