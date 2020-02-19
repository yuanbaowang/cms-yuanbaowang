/**
 * 
 */
package com.yuanbaowang.dao;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yuanbaowang.bean.Article;
import com.yuanbaowang.bean.Category;
import com.yuanbaowang.bean.Channel;
import com.yuanbaowang.bean.Comment;
import com.yuanbaowang.bean.Complain;
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
	@Insert("insert cms_article set title = #{a.title},content = #{a.content},picture = #{a.picture},channel_id = #{a.channelId},category_id = #{a.categoryId},user_id = #{a.user_id},hits = 0,hot = 0,status = 0,deleted = 0,created = now(),commentCnt = 0,articleType = #{a.articleType},complainCnt = 0")
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
	@Select("SELECT * FROM cms_article WHERE id = #{id}")
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
	List<Article> hotList(int pageNum,String title);



	/**
	 *	获取最新的文章
	 * @param pagesize 
	 */
	List<Article> lastList(int pagesize);


	/**
	 *	获取所有栏目下的分类
	 */
	@Select("SELECT id,name,channel_id FROM cms_category WHERE 	channel_id = #{channelId}")
	List<Category> getCategoryByChannel(@Param("channelId")int channelId);


	/**
	 * 获取所有分类下的文章
	 */
	List<Article> getArticle(@Param("channelId")int channelId, @Param("categoryId")int categoryId);


	/**
	 *	发表评论
	 */
	@Insert("INSERT cms_comment SET articleId = #{c.articleId},userId = #{c.userId},content = #{c.content},created = now()")
	int addComment(@Param("c")Comment com);


	/**
	 *	修改用户中的评论数量
	 */
	@Update("UPDATE cms_article SET commentCnt = commentCnt+1 WHERE id = #{value}")
	void updaCommentCnt(Integer userId);


	/**
	 * 根据文章id 获取所有的评论
	 */
	@Select("SELECT * FROM cms_comment as c LEFT JOIN cms_user as u ON u.id = c.userId WHERE articleId = #{value}")
	List<Comment> getComments(int id);


	/**
	 *	添加投诉信息
	 */
	@Insert("INSERT INTO cms_complain(article_id,user_id,complain_type,compain_option,src_url,picture,content,email,mobile,created) VALUES(#{articleId},#{userId},#{complainType},#{compainOption},#{srcUrl},#{picture},#{content},#{email},#{mobile},now())")
	int addComplain(@Valid Complain complain);

	/**
	 *	修改文章表中的投诉量
	 */
	@Update("UPDATE cms_article SET complainCnt = complainCnt+1,status = if(complainCnt>10,2,status) WHERE id = #{value}")
	void updArticleComplain(Integer articleId);


	/**
	 *	查询没有通过的文章
	 */
	@Select("SELECT * FROM cms_article WHERE status = 1 AND hot = 1")
	List<Article> getArticleNo();


	/**
	 * @param hits 
	 *
	 */
	@Update("UPDATE cms_article set hits = #{hits} where id = #{id} ")
	int updHits(@Param("id")Integer valueOf, @Param("hits")Integer hits);


	
}
