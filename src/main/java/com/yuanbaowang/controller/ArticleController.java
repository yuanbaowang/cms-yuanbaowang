/**
 * 
 */
package com.yuanbaowang.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.yuanbaowang.bean.Article;
import com.yuanbaowang.bean.Collect;
import com.yuanbaowang.bean.Comment;
import com.yuanbaowang.bean.Complain;
import com.yuanbaowang.bean.User;
import com.yuanbaowang.common.CmsContant;
import com.yuanbaowang.common.CmsError;
import com.yuanbaowang.common.CmsMessage;
import com.yuanbaowang.service.ArticleService;
import com.yuanbaowang.service.CollectService;

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
	
	@Autowired
	KafkaTemplate<String, String> tem;
	
	//注入线程池
	ThreadPoolTaskExecutor executor;
	
	@Autowired
	CollectService collectService;
	
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
		System.err.println("id为："+id);
//		executor.execute(new Runnable() {
//			
//			@Override
//			public void run() {
				//修改点击量
				request.getRemoteAddr();
				String jsonString = JSON.toJSONString(id);
				tem.send("article", "upd="+jsonString);
				System.err.println("发送成功！");
//			}
//		});
		
		Article byId = service.getById(id);
		PageInfo<Article> hotList = service.hotList(pageNum,"");
		List<Article> list = hotList.getList();
		request.getSession().setAttribute("hotList", hotList);
		request.getSession().setAttribute("article", byId);
		request.getSession().setAttribute("id", id);
		return "detail";
	}
	
	/**
	 * 	添加至收藏夹
	 */
	@RequestMapping("addCollect")
	@ResponseBody
	public CmsMessage addCollect(HttpServletRequest request, int id) {
		//获取当前登录用户
		User user = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		if(user == null) {
			return new CmsMessage(CmsContant.NOT_LOGIN,"您尚未登录，不可添加至收藏夹",null);
		}
		//根据id 查询文章
		Article byId = service.getById(id);
		//创建建收藏夹对象并赋值
		Collect collect = new Collect();
		collect.setUser_id(user.getId());
		collect.setUrl("http://localhost:8083/article/detail?id="+id);
		collect.setText(byId.getTitle());

		//执行 添加 数据库语句
		int i = collectService.addCollect(collect);
		//返回前天台数据
		if(i > 0 ) {
			return new CmsMessage(CmsError.SUCCESS,"已成功添加至我的收藏夹！",null);
		}
			return new CmsMessage(CmsError.NO_EXIST,"添加失败失败,您的url地址存在问题！",null);
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
		String processFile = processFile(file);
		complain.setPicture(processFile);
		//没有错误就添加
		int i = service.addComplain(complain);
		return "redirect:detail?id="+complain.getArticleId();
	}


	
}
