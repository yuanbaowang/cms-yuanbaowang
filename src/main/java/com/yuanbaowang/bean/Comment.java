/**
 * 
 */
package com.yuanbaowang.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 袁保旺 评论 2019年12月19日 下午9:33:40
 */
public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3369845275480842730L;

	private Integer id;
	private Integer articleId;
	private Integer userId;
	private String content;
	private Date created;
	private String username;

	/**
	 * @return id get方法
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id set方法
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return username get方法
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username set方法
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return articleId get方法
	 */
	public Integer getArticleId() {
		return articleId;
	}

	/**
	 * @param articleId set方法
	 */
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	/**
	 * @return userId get方法
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId set方法
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articleId == null) ? 0 : articleId.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		Comment other = (Comment) obj;
		if (articleId == null) {
			if (other.articleId != null)
				return false;
		} else if (!articleId.equals(other.articleId))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", articleId=" + articleId + ", userId=" + userId + ", content=" + content
				+ ", created=" + created + "]";
	}

}
