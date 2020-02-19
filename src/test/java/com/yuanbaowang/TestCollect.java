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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanbaowang.bean.Collect;
import com.yuanbaowang.service.CollectService;

import yuanbaowang_cms_utils.StringUtils;

/**
 * @author 袁保旺
 *
 * 2020年2月18日 下午12:37:01 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class TestCollect {
	@Autowired
	CollectService service;
	
	/**
	 * 	测试添加
	 */
	@Test
	public void add() {
		Collect collect = new Collect();
		collect.setText("测试测试");
		//判断是否为合法的url
		if(StringUtils.isUrl(collect.getUrl())) {
			collect.setUrl("http://www.baidu.com");
		}else {
			System.out.println("url不正确");
			return;
		}
		collect.setUser_id(70);
		int addCollect = service.addCollect(collect);
		System.out.println(addCollect);
	}
	
	/**
	 * 	测试删除
	 */
	@Test
	public void del() {
		int addCollect = service.delCollect(80, 1);
		System.out.println(addCollect);
	}
	
	/**
	 * 	测试查询
	 */
	@Test
	public void sel() {
		int page = 1;
		PageHelper.startPage(page, 4);
		PageInfo<Collect> selCollect = service.selCollect(80, page);
		List<Collect> list = selCollect.getList();
		for (Collect collect : list) {
			System.out.println(collect);
		}
		
	}
	
	

}
