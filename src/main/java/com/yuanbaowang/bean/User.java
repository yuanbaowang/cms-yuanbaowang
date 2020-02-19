/**
 * 
 */
package com.yuanbaowang.bean;

import java.io.Serializable;
import java.util.Date;


import javax.validation.constraints.Size;

import org.apache.ibatis.javassist.SerialVersionUID;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author 袁保旺
 *
 *         2019年12月10日 上午11:00:13
 */
public class User implements Serializable {

	private static final long serialVersionUID = -2666232310561389698L;

	private Integer id;
	@NotBlank(message = "用户名不可为空")
	@Size(max = 16, min = 2, message = "最大16位，最小2位")
	private String username;
	@NotBlank(message = "用户名不可为空")
	@Size(max = 16, min = 2, message = "最大12位，最小6位")
	private String password;
	private String nickname;
	private Date birthday;
	private int gender;
	private int locked;
	private Date create_time;
	private Date update_time;
	private String url;
	private String score;// 积分
	private int role;// 角色
	private Integer mdl;

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
	 * @return mdl get方法
	 */
	public Integer getMdl() {
		return mdl;
	}

	/**
	 * @param mdl set方法
	 */
	public void setMdl(Integer mdl) {
		this.mdl = mdl;
	}

	/**
	 * @param username set方法
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return password get方法
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password set方法
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return nickname get方法
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname set方法
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return birthday get方法
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday set方法
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return gender get方法
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * @param gender set方法
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * @return locked get方法
	 */
	public int getLocked() {
		return locked;
	}

	/**
	 * @param locked set方法
	 */
	public void setLocked(int locked) {
		this.locked = locked;
	}

	/**
	 * @return create_time get方法
	 */
	public Date getCreate_time() {
		return create_time;
	}

	/**
	 * @param create_time set方法
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	/**
	 * @return update_time get方法
	 */
	public Date getUpdate_time() {
		return update_time;
	}

	/**
	 * @param update_time set方法
	 */
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
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
	 * @return score get方法
	 */
	public String getScore() {
		return score;
	}

	/**
	 * @param score set方法
	 */
	public void setScore(String score) {
		this.score = score;
	}

	/**
	 * @return role get方法
	 */
	public int getRole() {
		return role;
	}

	/**
	 * @param role set方法
	 */
	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", birthday=" + birthday + ", gender=" + gender + ", locked=" + locked + ", create_time="
				+ create_time + ", update_time=" + update_time + ", url=" + url + ", score=" + score + ", role=" + role
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
