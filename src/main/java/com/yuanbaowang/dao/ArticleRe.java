/**
 * 
 */
package com.yuanbaowang.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.yuanbaowang.bean.Article;

/**
 * @author 袁保旺
 *
 * 2020年1月11日 上午10:07:28 
 */
public interface ArticleRe extends ElasticsearchRepository<Article, Integer>{

	/**
	 *	根据标题查询文章
	 */
	List<Article> findByTitle(String title);


}
