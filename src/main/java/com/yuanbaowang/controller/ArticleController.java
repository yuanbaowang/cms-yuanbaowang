/**
 * 
 */
package com.yuanbaowang.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.yuanbaowang.bean.Article;
import com.yuanbaowang.bean.Comment;
import com.yuanbaowang.bean.Complain;
import com.yuanbaowang.bean.User;
import com.yuanbaowang.common.CmsContant;
import com.yuanbaowang.common.CmsError;
import com.yuanbaowang.common.CmsMessage;
import com.yuanbaowang.service.ArticleService;

import yuanbaowang_cms_utils.StringUtils;

/**
 * @author 袁保旺
 *
 * 2019年12月17日 下午2:39:19 
 */
@Controller
@RequestMapping("article")
public class ArticleController extends BaseController{
	
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
	public String detail(HttpServletRequest request,int id,@RequestParam(defaultValue = "1") int pageNum) {
		Article byId = service.getById(id);
		PageInfo<Article> hotList = service.hotList(pageNum);
		List<Article> list = hotList.getList();
		request.getSession().setAttribute("hotList", hotList);
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
	
	/**
	 * 	去往投诉
	 */
	@RequestMapping(value = "complain",method = RequestMethod.GET)
	public String complain(HttpServletRequest request,int articleId) {
		//获取文章id 获得文章
		Article article = service.getById(articleId);
		request.getSession().setAttribute("article", article);
		request.setAttribute("complain", new Complain());
		return "article/complain";
	}
	
	/**
	 * 	接收投诉页面发送过来的数据
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = "complain",method = RequestMethod.POST)
	public String complain(HttpServletRequest request, @ModelAttribute("complain") @Valid Complain complain,BindingResult result,MultipartFile file) throws IllegalStateException, IOException {
		//判断数据是否正确
		if(!StringUtils.isUrl(complain.getSrcUrl())) {
			result.rejectValue("srcUrl", "","Url网址错误！");
		}
		if(result.hasErrors()) {
			return "article/complain";
		}
		User user = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		if(user != null) {
			//投诉人
			complain.setUserId(user.getId());
		}else {
			complain.setUserId(0);
		}
		String processFile = this.processFile(file);
		complain.setPicture(processFile);
		//没有错误就添加
		int i = service.addComplain(complain);
		return "redirect:detail?id="+complain.getArticleId();
	}
	
}
