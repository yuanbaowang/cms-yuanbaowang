/**
 * 
 */
package com.yuanbaowang.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.yuanbaowang.bean.Article;
import com.yuanbaowang.bean.Category;
import com.yuanbaowang.bean.Channel;
import com.yuanbaowang.bean.Slide;
import com.yuanbaowang.common.CmsContant;
import com.yuanbaowang.dao.ArticleRe;
import com.yuanbaowang.service.ArticleService;

import highlight.HLUtils;

/**
 * @author 袁保旺
 *
 * 2019年12月18日 下午1:35:51 
 */
@Controller
public class IndexController {
	@Autowired
	private ArticleService service;
	
	@Autowired
	private RedisTemplate redisTemlate;
	
	@Autowired
	ArticleRe articleRe;
	
	@Autowired
	RedisTemplate redisTemplate;
	
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;
	
	/**
	 * 	主页 获取所有的了栏目
	 * @throws InterruptedException 
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request,@RequestParam(defaultValue = "1")int pageNum,String title) throws InterruptedException {
		Thread t1 = new Thread() {
			public void run() {
				//获取所有的栏目
				List<Channel> channelList = service.getChannels();
				request.getSession().setAttribute("channelList", channelList);
			};
		};
		 
		Thread t2 = new Thread() {
			public void run() {
				if(title == null) {
					//获取热门文章
					PageInfo<Article> hotList = service.hotList(pageNum,title);
					request.getSession().setAttribute("hotList", hotList);
				 }
				if(title != null && !title.equals("")){ 
					//没有高亮显示
//					 PageHelper.startPage(pageNum,5);
//					 List<Article> list = articleRe.findByTitle(title); 
//					 PageInfo<Article> hotList = new PageInfo<Article>(list);
//					 request.getSession().setAttribute("hotList", hotList);
//					 request.getSession().setAttribute("title", title);
					//高亮显示
					
					long currentTimeMillis = System.currentTimeMillis();
					
					
					 PageInfo<Article> hotList = (PageInfo<Article>) HLUtils.findByHighLight(elasticsearchTemplate, Article.class, pageNum, CmsContant.PAGESIZE, new String[] {"title"}, "id", title);
					 long current = System.currentTimeMillis();
					long hs = current -currentTimeMillis;
					System.err.println("共耗时"+hs);
					 request.getSession().setAttribute("hs", hs);
					 request.getSession().setAttribute("hotList", hotList);
					 request.getSession().setAttribute("title", title);
				}
			};
		};
		
		Thread t3 = new Thread() {
			public void run() {
				//从reidis中查询最新文章
				//System.err.println("从redis中查找数据。。。。。");
				List<Article> range = redisTemlate.opsForList().range("article", (pageNum-1)*CmsContant.PAGESIZE, pageNum*CmsContant.PAGESIZE-1);
				System.err.println(range.size());
				//判断是否为空
				if(range == null || range.size() == 0) {
					//获取最后发布的文章
					List<Article> lastList = service.lastList();
				//	System.err.println("为空，从mysql中往redis中添加数据。。。。。");
					//添加到redis数据库中
					redisTemlate.opsForList().leftPushAll("article", lastList.toArray());
					//设置过期时间
					redisTemlate.expire("article", 5, TimeUnit.MINUTES);
					request.getSession().setAttribute("lastList", lastList);
				}else{
					//System.err.println("redis 返回前台数据！");
					request.getSession().setAttribute("lastList", range);
				}
				
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

		return "index";
	}
	
	/**
	 * 	栏目分类
	 * @throws InterruptedException 
	 */
	@RequestMapping("channel")
	public String channel(HttpServletRequest request,int channelId,@RequestParam(defaultValue = "0")int categoryId,@RequestParam(defaultValue = "1")int pageNum,String title) throws InterruptedException {
		Thread t1 = new Thread() {
			public void run() {
				//获取所有的栏目
				List<Channel> channelList = service.getChannels();
				request.getSession().setAttribute("channelList", channelList);
			};
		};
		
		Thread t2 = new Thread() {
			public void run() {
				if(title == null || title.equals("")) {
					//获取当前分类中的文章
					PageInfo<Article> article = service.getArticle(channelId,categoryId,pageNum);
					request.getSession().setAttribute("article", article);
				}
				
				if(title != null && !title.equals("")){ 
					//没有高亮显示
//					 PageHelper.startPage(pageNum,5);
//					 List<Article> list = articleRe.findByTitle(title); 
//					 PageInfo<Article> hotList = new PageInfo<Article>(list);
//					 request.getSession().setAttribute("hotList", hotList);
//					 request.getSession().setAttribute("title", title);
					//高亮显示
					long currentTimeMillis = System.currentTimeMillis();
					 PageInfo<Article> article = (PageInfo<Article>) HLUtils.findByHighLight(elasticsearchTemplate, Article.class,  pageNum, CmsContant.PAGESIZE, new String[] {"title"}, "id", title);
					 long current = System.currentTimeMillis();
					long hs = current -currentTimeMillis;
					System.err.println("共耗时"+hs);
					 request.getSession().setAttribute("hs", hs);
					 request.getSession().setAttribute("article", article);
					 request.getSession().setAttribute("title", title);
				}
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
		
		Thread t5 = new Thread() {
			public void run() {
				//获取当前栏目下所有的分类
				List<Category> category = service.getCategoryByChannel(channelId);
				request.getSession().setAttribute("category", category);
			};
		};
		request.getSession().setAttribute("categoryId", categoryId);
		request.getSession().setAttribute("channelId", channelId);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		return "channel";
	}
	
}
