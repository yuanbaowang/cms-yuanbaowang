/**
 * 
 */
package com.yuanbaowang;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yuanbaowang.bean.Article;
import com.yuanbaowang.dao.ArticleRe;
import com.yuanbaowang.service.ArticleService;

/**
 * @author 袁保旺
 *
 * 2020年1月11日 上午10:20:30 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class TestMyEs {
	@Autowired
	ArticleService service;
	
	@Autowired
	ArticleRe articleRe;
	
	
	@Test
	public void testEsAdd() {
	List<Article> list = service.getArticleNo();
	//从mysql 中查询 审核没有通过的数据 存储到Es 数据库中
	articleRe.saveAll(list);
		
		
	}
}
