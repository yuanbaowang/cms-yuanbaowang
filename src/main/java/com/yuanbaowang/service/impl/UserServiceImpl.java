/**
 * 
 */
package com.yuanbaowang.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuanbaowang.bean.User;
import com.yuanbaowang.common.CmsEncryUtils;
import com.yuanbaowang.dao.UserMapper;
import com.yuanbaowang.service.UserService;

/**
 * @author 袁保旺
 *
 * 2019年12月10日 上午11:02:14 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	/**
	 * 	根据注册用户名 判断是否唯一
	 */
	@Override
	public User getUserByUsername(String username) {
		return mapper.getUserByUsername(username);
	}

	/**
	 * 	进行注册
	 */
	@Override
	public int register(@Valid User u) {
		//设置密码 计算密文
		u.setPassword(CmsEncryUtils.Encry(u.getPassword(),u.getUsername()));
		return mapper.register(u);
	}

	@Override
	public User login(User u) {
		u.setPassword(CmsEncryUtils.Encry(u.getPassword(),u.getUsername()));
		return mapper.login(u);
	}
}
