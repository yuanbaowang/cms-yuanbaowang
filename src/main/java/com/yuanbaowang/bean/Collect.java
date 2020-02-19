/**
 * 
 */
package com.yuanbaowang.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author 袁保旺
 *
 *         2020年2月18日 上午9:25:46
 */
public class Collect implements Serializable {
	private Integer id;
	private String text;
	private String url;
	private Integer user_id;
	private Date created;

	/**
	 * 构造方法
	 */
	public Collect(Integer id, String text, String url, Integer user_id, Date created) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
		this.user_id = user_id;
		this.created = created;
	}

	/**
	 * 构造方法
	 */
	public Collect() {
		super();
		// TODO Auto-generated constructor stub
	}

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
	 * @return text get方法
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text set方法
	 */
	public void setText(String text) {
		this.text = text;
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

	/**
	 * @return user_id get方法
	 */
	public Integer getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id set方法
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
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
	public String toString() {
		return "Collect [id=" + id + ", text=" + text + ", url=" + url + ", user_id=" + user_id + ", created=" + created
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Collect other = (Collect) obj;
		return Objects.equals(id, other.id) && Objects.equals(url, other.url);
	}

}
