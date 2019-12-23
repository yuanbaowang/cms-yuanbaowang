/**
 * 
 */
package com.yuanbaowang.service;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.yuanbaowang.bean.Article;
import com.yuanbaowang.bean.Category;
import com.yuanbaowang.bean.Channel;
import com.yuanbaowang.bean.Comment;
import com.yuanbaowang.bean.Complain;
import com.yuanbaowang.bean.Slide;

/**
 * @author 袁保旺
 *
 * 2019年12月12日 下午8:03:32 
 */
public interface ArticleService {

	/**
	 *	根据用户查询文章列表
	 */
	PageInfo<Article> listByUser(Integer id, int pageNum);

	/**
	 *	删除文章
	 */
	int delArticle(int id);

	/**
	 *	查询所有channel对象
	 */
	List<Channel> channelList();
	
	/**
	 *	根据栏目id 查询所有分类对象
	 */
	List<Category> getCateGorisByCid(int id);

	/**
	 *	发布文章，向数据库中添加数据
	 */
	int add(Article article);

	/**
	 *	根据文章id 获取文章对象
	 */
	Article getById(int id);
	
	
	/**
	 *	根据文章id 获取文章对象
	 */
	Article getSimpleById(int id);
	

	/**
	 *	修改文章
	 */
	int upd(Article article, Integer id);

	/**
	 *	获取管理员文章列表
	 */
	PageInfo<Article> list(int status, int pageNum);

	/**
	 *	修改状态
	 */
	int setStatus(int id, int status);

	/**
	 *	设置热门
	 */
	int setHot(int id, int status);

	/**
	 *	获取所有栏目
	 */
	List<Channel> getChannels();

	/**
	 *	获取热门文章
	 */
	PageInfo<Article> hotList(int pageNum);

	/**
	 *	获取最后发布的文章
	 */
	List<Article> lastList();

	/**
	 *	获取所有轮播图
	 */
	List<Slide> slideList();

	/**
	 *	根据channelId获取当前栏目下所有的分类
	 */
	List<Category> getCategoryByChannel(int channelId);

	/**
	 * 获取当前栏目下的文章
	 */
	PageInfo<Article> getArticle(int channelId, int categoryId, int pageNum);

	/**
	 *	发表评论
	 */
	int addComment(Comment com);

	/**
	 *	文章id 获取所有评论
	 */
	PageInfo<Comment> getComments(int id, int pageNum);

	/**
	 *	添加投诉
	 */
	int addComplain(@Valid Complain complain);



}
