/**
 * 
 */
package com.yuanbaowang.service;

import javax.validation.Valid;

import com.yuanbaowang.bean.User;

/**
 * @author 袁保旺
 *
 * 2019年12月10日 上午11:01:31 
 */
public interface UserService {

	/**
	 * 	根据注册用户名 判断是否唯一
	 */
	User getUserByUsername(String username);

	/**
	 *	进行注册
	 */
	int register(@Valid User u);

	/**
	 *	登录操作
	 */
	User login(User u);

}
