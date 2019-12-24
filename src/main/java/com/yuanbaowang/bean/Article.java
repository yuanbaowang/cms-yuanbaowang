/**
 * 
 */
package com.yuanbaowang.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 袁保旺
 *
 *         2019年12月12日 下午7:39:04 文章实体类
 */
public class Article implements Serializable {

	private static final long serialVersionUID = 1921362106209183884L;

	private Integer id;// 编号
	private String title;// 标题
	private String picture;// 图片
	private String content;// 内容
	private String icture;// 栏目频道
	private int channelId;// 分类
	private int categoryId;
	private int user_id;
	private int hits;// 点击次数
	private int hot;// 是否热门
	private Integer status;// 文章状态 0待审核，1审核通过，2拒绝
	private int deleted;// 是否被删除
	private Date created;// 创建时间
	private Date updated;// 最后的修改时间
	private int commentCnt;// 评论数量
	private int articleType;// 文章的类型个文字0，图片1
	private int complainCnt;// 投诉数量

	private Channel channel;
	private Category category;
	private User user;

	/**
	 * @return id get方法
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return complainCnt get方法
	 */
	public int getComplainCnt() {
		return complainCnt;
	}

	/**
	 * @param complainCnt set方法
	 */
	public void setComplainCnt(int complainCnt) {
		this.complainCnt = complainCnt;
	}

	/**
	 * @param id set方法
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return title get方法
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title set方法
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return content get方法
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content set方法
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return icture get方法
	 */
	public String getIcture() {
		return icture;
	}

	/**
	 * @param icture set方法
	 */
	public void setIcture(String icture) {
		this.icture = icture;
	}

	/**
	 * @return user_id get方法
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id set方法
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return hits get方法
	 */
	public int getHits() {
		return hits;
	}

	/**
	 * @param hits set方法
	 */
	public void setHits(int hits) {
		this.hits = hits;
	}

	/**
	 * @return hot get方法
	 */
	public int getHot() {
		return hot;
	}

	/**
	 * @param hot set方法
	 */
	public void setHot(int hot) {
		this.hot = hot;
	}

	/**
	 * @return status get方法
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status set方法
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return deleted get方法
	 */
	public int getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted set方法
	 */
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return created get方法
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created set方法
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return updated get方法
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * @param updated set方法
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	/**
	 * @return commentCnt get方法
	 */
	public int getCommentCnt() {
		return commentCnt;
	}

	/**
	 * @param commentCnt set方法
	 */
	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}

	/**
	 * @return articleType get方法
	 */
	public int getArticleType() {
		return articleType;
	}

	/**
	 * @param articleType set方法
	 */
	public void setArticleType(int articleType) {
		this.articleType = articleType;
	}

	/**
	 * @return channel get方法
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * @param channel set方法
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	/**
	 * @return category get方法
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category set方法
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return user get方法
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user set方法
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return picture get方法
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * @param picture set方法
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + articleType;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (articleType != other.articleType)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * @return channelId get方法
	 */
	public int getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId set方法
	 */
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	/**
	 * @return categoryId get方法
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId set方法
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", picture=" + picture + ", content=" + content + ", icture="
				+ icture + ", channelId=" + channelId + ", categoryId=" + categoryId + ", user_id=" + user_id
				+ ", hits=" + hits + ", hot=" + hot + ", status=" + status + ", deleted=" + deleted + ", created="
				+ created + ", updated=" + updated + ", commentCnt=" + commentCnt + ", articleType=" + articleType
				+ ", channel=" + channel + ", category=" + category + ", user=" + user + "]";
	}

}
