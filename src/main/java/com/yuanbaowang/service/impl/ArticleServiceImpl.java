/**
 * 
 */
package com.yuanbaowang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanbaowang.bean.Article;
import com.yuanbaowang.bean.Category;
import com.yuanbaowang.bean.Channel;
import com.yuanbaowang.bean.Slide;
import com.yuanbaowang.common.CmsContant;
import com.yuanbaowang.dao.ArticleMapper;
import com.yuanbaowang.dao.ChannelMapper;
import com.yuanbaowang.dao.SlideMapper;
import com.yuanbaowang.service.ArticleService;

/**
 * @author 袁保旺
 *
 * 2019年12月12日 下午8:05:06 
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleMapper mapper;
	
	@Autowired
	private SlideMapper slidMapper;
	
	@Autowired
	private ChannelMapper channelMapper;

	/**
	 * 	根据用户寻找文章
	 */
	@Override
	public PageInfo<Article> listByUser(Integer id, int pageNum) {
		PageHelper.startPage(pageNum,CmsContant.PAGESIZE);
		PageInfo<Article> pageInfo = new PageInfo<Article>(mapper.listByUser(id));
		return pageInfo;
	}

	/**
	 * 	根据文章id删除文章
	 */
	@Override
	public int delArticle(int id) {
		
		return mapper.updateStatus(id,CmsContant.ARTICLE_STATUS_DEL);
	}

	/**
	 * 	获取所有channel对象
	 */
	@Override
	public List<Channel> channelList() {
		return mapper.channelList();
	}

	@Override
	public List<Category> getCateGorisByCid(int id) {
		return mapper.getCateGorisByCid(id);
	}

	/**
	 * 	发布文章，向数据库中添加文章数据
	 */
	@Override
	public int add(Article article) {
		return mapper.add(article);
	}

	/**
	 * 	根据文章id 获取文章对象
	 */
	@Override
	public Article getById(int id) {
		return mapper.getByIdArt(id);
	}

	/**
	 * 	修改文章
	 */
	@Override
	public int upd(Article article, Integer id) {
		Article articleSrc = this.getById(article.getId());
		if(articleSrc.getUser_id() != id) {
			//如果文章修改不一致 进行抛出异常
		}
		return mapper.upd(article);
	}

	/**
	 * 	管理员，列表
	 */
	@Override
	public PageInfo<Article> list(int status, int pageNum) {
		PageHelper.startPage(pageNum, CmsContant.PAGESIZE);
		return new PageInfo<Article>(mapper.list(status));
	}

	/**
	 * 获取简单的文章信息
	 */
	@Override
	public Article getSimpleById(int id) {
		return mapper.getSimpleById(id);
	}

	/**
	 * 设置状态
	 */
	@Override
	public int setStatus(int id, int status) {
		return mapper.setStatus(id,status);
	}

	/**
	 * 设置热度
	 */
	@Override
	public int setHot(int id, int status) {
		return mapper.setHot(id,status);
	}

	/**
	 * 	获取所有栏目
	 */
	@Override
	public List<Channel> getChannels() {
		return channelMapper.getChannels();
	}

	/**
	 * 	获取所有热门文章
	 */
	@Override
	public PageInfo<Article> hotList(int pageNum) {
		PageHelper.startPage(pageNum,CmsContant.PAGESIZE);
		return new PageInfo<Article>(mapper.hotList(pageNum));
	}

	/**
	 * 	获取最新的文章
	 */
	@Override
	public List<Article> lastList() {
		return mapper.lastList(CmsContant.PAGESIZE);
	}

	/**
	 * 	获取所有的轮播图
	 */
	@Override
	public List<Slide> slideList() {
		return slidMapper.slideList();
	}
	
}
