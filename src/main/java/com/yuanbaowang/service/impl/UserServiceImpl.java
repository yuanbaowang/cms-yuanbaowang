/**
 * 
 */
package com.yuanbaowang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
