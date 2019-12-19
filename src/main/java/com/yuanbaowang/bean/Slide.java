/**
 * 
 */
package com.yuanbaowang.bean;

import java.io.Serializable;

/**
 * @author 袁保旺 轮播图实体类 2019年12月18日 下午1:55:25
 */
public class Slide implements Serializable {

	private static final long serialVersionUID = 2938816711801230828L;

	private Integer id;
	private String title;
	private String picture;
	private String url;

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

	/**
	 * @return url get方法
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url set方法
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Slide other = (Slide) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Slide [id=" + id + ", title=" + title + ", picture=" + picture + ", url=" + url + "]";
	}
	

}
