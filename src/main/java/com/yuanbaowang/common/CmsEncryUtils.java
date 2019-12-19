/**
 * 
 */
package com.yuanbaowang.common;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author 袁保旺
 *
 * 2019年12月11日 下午2:05:40 
 * 	加密工具类
 * 
 */
public class CmsEncryUtils {

	/**
	 * 	加盐 加密
	 */
	public static String Encry(String src,String salt) {
		byte[] md5 = DigestUtils.md5(salt+"src"+salt);
		return new String(md5);
	}	
	
}
