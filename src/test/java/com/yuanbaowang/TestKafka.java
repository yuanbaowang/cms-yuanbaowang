/**
 * 
 */
package com.yuanbaowang;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.yuanbaowang.bean.Article;
import com.yuanbaowang.service.ArticleService;

import yuanbaowang_cms_utils.FileUtils;

/**
 * @author 袁保旺
 *
 * 2020年2月13日 下午1:54:29 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class TestKafka {
	
	@Autowired
	KafkaTemplate<String, String> tem;
	
	@Autowired
	ArticleService service;
	
	//添加
	@Test
	public void testAdd() throws IOException {
		File file = new File("E:\\文章");
		String[] list = file.list();
		for (String str : list) {
			List<String> readFile = FileUtils.readFile("E:\\文章\\"+str);
				Article article = new Article();
				article.setTitle(str);
				article.setPicture(null);
				article.setContent(readFile.toString());
				int random = (int)(Math.random()*2);
				article.setHot(random);
				int c = (int)(1+Math.random()*8);
				int c1 = (int)(1+Math.random()*28);
				article.setArticleType(0);
				article.setChannelId(c);
				article.setUser_id(81);
				article.setCategoryId(c1);
				String jsonString = JSON.toJSONString(article);
				
				tem.send("article","all="+jsonString);
				
				System.err.println("发送成功！");
			
		}
		
	}

}
