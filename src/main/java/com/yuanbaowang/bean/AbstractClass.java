/**
 * 
 */
package com.yuanbaowang.bean;

import java.io.Serializable;

/**
 * @author 袁保旺
 *
 *         2019年12月12日 下午7:49:37 抽象类
 */
public abstract class AbstractClass implements Serializable {

	private static final long serialVersionUID = -4116684147173379599L;

	private Integer id;
	private String name;

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
	 * @return name get方法
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name set方法
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		AbstractClass other = (AbstractClass) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractClass [id=" + id + ", name=" + name + "]";
	}

}
