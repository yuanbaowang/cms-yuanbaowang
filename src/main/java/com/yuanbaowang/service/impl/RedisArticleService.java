/**
 * 
 */
package com.yuanbaowang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yuanbaowang.bean.Article;

/**
 * @author 袁保旺
 *
 * 2020年2月17日 下午4:27:29 
 */
@Service
public class RedisArticleService {
	//注入redis
	@Autowired
	RedisTemplate<String, String>   redisTem;
	
	//注入kafka
	@Autowired
	KafkaTemplate<String, String> kafkaTem;
	
	
	/**
	 * @param article 
	 *
	 */
	public int save(Article article) {
		
		System.err.println("进入方法");
		String jsonString = JSON.toJSONString(article);
		redisTem.opsForValue().set("key_add", jsonString);
	
		kafkaTem.send("article", "key_add");
		return 1;
	}
	
	

}
