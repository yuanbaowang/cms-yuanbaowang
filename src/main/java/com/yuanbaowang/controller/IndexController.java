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

import com.github.pagehelper.PageInfo;
import com.yuanbaowang.bean.Article;
import com.yuanbaowang.bean.Channel;
import com.yuanbaowang.bean.Slide;
import com.yuanbaowang.service.ArticleService;

/**
 * @author 袁保旺
 *
 * 2019年12月18日 下午1:35:51 
 */
@Controller
@RequestMapping("index")
public class IndexController {
	@Autowired
	private ArticleService service;
	
	/**
	 * 	主页 获取所有的了栏目
	 * @throws InterruptedException 
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request,@RequestParam(defaultValue = "1")int pageNum) throws InterruptedException {
		Thread t1 = new Thread() {
			public void run() {
				//获取所有的栏目
				List<Channel> channelList = service.getChannels();
				request.getSession().setAttribute("channelList", channelList);
			};
		};
		
		Thread t2 = new Thread() {
			public void run() {
				//获取热门文章
				PageInfo<Article> hotList = service.hotList(pageNum);
				request.getSession().setAttribute("hotList", hotList);
			};
		};
		

		Thread t3 = new Thread() {
			public void run() {
				//获取最后发布的文章
				List<Article> lastList = service.lastList();
				request.getSession().setAttribute("lastList", lastList);
			};
		};
		
		
		Thread t4 = new Thread() {
			public void run() {
				//获取轮播图
				List<Slide> slideList = service.slideList();
				request.getSession().setAttribute("slideList", slideList);
			};
		};
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();

		return "admin/index";

	}

}
