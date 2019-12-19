package com.yuanbaowang.common;
import java.io.Serializable;

/**
 * @author 袁保旺 消息统一处理 2019年12月17日 下午2:30:42
 */
public class CmsMessage implements Serializable {

	private static final long serialVersionUID = -1352845007295086567L;
	int code; //1代表成功  2代表失败
	String error; //错误信息
	Object date; //成功 返回的数据

	/**
	 * @return code get方法
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code set方法
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return error get方法
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error set方法
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return date get方法
	 */
	public Object getDate() {
		return date;
	}

	/**
	 * @param date set方法
	 */
	public void setDate(Object date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((error == null) ? 0 : error.hashCode());
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
		CmsMessage other = (CmsMessage) obj;
		if (code != other.code)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (error == null) {
			if (other.error != null)
				return false;
		} else if (!error.equals(other.error))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CmsMessage [code=" + code + ", error=" + error + ", date=" + date + "]";
	}

	/**
	 * 构造方法
	 */
	public CmsMessage(int code, String error, Object date) {
		super();
		this.code = code;
		this.error = error;
		this.date = date;
	}

	/**
	 * 构造方法
	 */
	public CmsMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

}
