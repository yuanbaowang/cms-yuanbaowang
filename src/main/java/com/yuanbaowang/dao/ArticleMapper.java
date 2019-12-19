/**
 * 
 */
package com.yuanbaowang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yuanbaowang.bean.Article;
import com.yuanbaowang.bean.Category;
import com.yuanbaowang.bean.Channel;
import com.yuanbaowang.bean.Slide;

/**
 * @author 袁保旺
 *
 * 2019年12月12日 下午8:03:10 
 */
public interface ArticleMapper {

	/**
	 *	根据用户id 查找用户文章
	 */
	List<Article> listByUser(@Param("id")Integer id);


	/**
	 *	删除文章  修改到删除的状态
	 */ 
	@Update("UPDATE cms_article SET deleted = #{status} WHERE id = #{id}")
	int updateStatus(@Param("id")int id, @Param("status")int articleStatusDel);


	/**
	 *	查询所有的channel对象
	 */
	@Select("select id,name from cms_channel")
	List<Channel> channelList();

	
	/**
	 *	根据栏目id 查询所有的分类对象
	 */
	@Select("select id,name from cms_category where channel_id = #{id}")
	List<Category> getCateGorisByCid(@Param("id")int id);


	/**
	 *	发布文章，向数据库中添加文章
	 */
	@Insert("insert cms_article set title = #{a.title},content = #{a.content},picture = #{a.picture},channel_id = #{a.channelId},category_id = #{a.categoryId},user_id = #{a.user_id},hits = 0,hot = 0,status = 1,deleted = 0,created = now(),commentCnt = 0,articleType = #{a.articleType}")
	int add(@Param("a")Article article);


	/**
	 *	根据文章id对象获取 获取文章对象
	 */
	Article getByIdArt(@Param("id")int id);


	/**
	 *	修改文章
	 */
	@Update("update cms_article set title = #{a.title},content = #{a.content},picture = #{a.picture},channel_id = #{a.channelId},category_id = #{a.categoryId},user_id = #{a.user_id},hits = 0,hot = 0,status = 1,deleted = 0,created = now(),commentCnt = 0,articleType = #{a.articleType} where id = #{a.id}")
	int upd(@Param("a")Article article);


	/**
	 *	获取管理员 文章列表
	 */
	List<Article> list(@Param("id")int status);


	/**
	 *	获取简单的文章信息
	 */
	@Select("SELECT id,title,channel_id,category_id,status,hot FROM cms_article WHERE id = #{id}")
	Article getSimpleById(@Param("id")int id);


	/**
	 *	设置状态
	 */
	@Update("UPDATE cms_article SET status = #{status} WHERE id = #{id}")
	int setStatus(@Param("id")int id, @Param("status")int status);


	/**
	 *	设置热门
	 */
	@Update("UPDATE cms_article SET hot = #{hot} WHERE id = #{id}")
	int setHot(@Param("id")int id,@Param("hot") int status);


	/**
	 *	获取所有热门文章
	 */
	List<Article> hotList(int pageNum);



	/**
	 *	获取最新的文章
	 * @param pagesize 
	 */
	List<Article> lastList(int pagesize);


	
}
