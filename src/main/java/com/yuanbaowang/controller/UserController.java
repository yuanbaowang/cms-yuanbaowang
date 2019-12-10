/**
 * 
 */
package com.yuanbaowang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuanbaowang.service.UserService;

/**
 * @author 袁保旺
 *
 * 2019年12月10日 上午11:02:32 
 */
@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService service;

	@RequestMapping("list.do")
	public String list() {
		return "user/list";
	}
}
