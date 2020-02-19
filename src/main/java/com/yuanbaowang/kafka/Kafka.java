/**
 * 
 */
package com.yuanbaowang.kafka;

import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.yuanbaowang.bean.Article;
import com.yuanbaowang.dao.ArticleMapper;
import com.yuanbaowang.dao.ArticleRe;

import yuanbaowang_cms_utils.StringUtils;

/**
 * @author 袁保旺
 *
 * 2020年1月8日 下午7:06:09 
 */
//消费者  接收发送过来的对象
public class Kafka implements MessageListener<String, String>{

	@Autowired
	ArticleMapper mapper;
	
	//注入es
	@Autowired
	ArticleRe articleRe;
	
	@Autowired
	RedisTemplate<String,String> tem;

	
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		System.err.println("接收成功！");
		
		String value = data.value();
		//添加
		String[] split = value.split("=");
		if(value.startsWith("add")) {
			Article article = JSON.parseObject(split[1],Article.class);
			articleRe.save(article);
			System.out.println("es已执行！！！");
			System.err.println("添加成功！");
		}
		
		if(value.startsWith("key")) {
			String string = tem.opsForValue().get(value);
			Article parseObject = JSON.parseObject(string, Article.class);
			if(parseObject != null) {
				int add = mapper.add(parseObject);
				if(add != 0) {
					String article = tem.opsForValue().get(value);
					if(article != null) {
						tem.delete(value);
						System.err.println("保存成功，已删除删除redis库中信息！");
					}
				}
			}
			
		}
		
		//删除
		if(value.startsWith("del")) {
			Article article = JSON.parseObject(split[1],Article.class);
			articleRe.deleteById(article.getId());
		} 
		
		//批量添加数据
		if(value.startsWith("all")) {
			Article parse = JSON.parseObject(split[1],Article.class);
			int add = mapper.add(parse);
			System.err.println(add);
		
		}
		
		//如果是id的话就执行 这个  把点击量进行一个修改
		if(value.startsWith("upd")) {
			System.err.println("进入");
			//查询对象
			Article sim = mapper.getSimpleById(Integer.parseInt(split[1]));
			Integer hits = sim.getHits();
			hits++;
			
			//存入redis
			Boolean hasKey = tem.hasKey("hits_$"+sim.getId()+"_$"+sim.getUser_id());
			if(!hasKey) {
				tem.opsForValue().set("hits_$"+sim.getId()+"_$"+sim.getUser_id(), "",5,TimeUnit.MINUTES);
			}
			//Article article = JSON.parseObject(split[1],Article.class);
			
			//修改点击量
			int i = mapper.updHits(sim.getId(),hits);
			System.err.println("修改成功！"+i);
		}
}


}
