/**
 * 
 */
package com.yuanbaowang.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.yuanbaowang.bean.User;
import com.yuanbaowang.common.CmsContant;
import com.yuanbaowang.service.UserService;

/**
 * @author 袁保旺
 *
 * 2019年12月11日 下午8:49:23 
 * 	配置拦截器
 */
public class CmsInterceptor implements HandlerInterceptor{
	@Autowired
	private UserService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception {
		User user = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		//不为空 
		if(user != null) {
			//放行
			return true;
		}
		User u = new User();
		//客户端获取cookie
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			//判断用户名是否一致
			if(cookies[i].getName().equals("username")) {
				u.setUsername(cookies[i].getValue());
			}
			//判断密码是否一致
			if(cookies[i].getName().equals("userpwd")) {
				u.setPassword(cookies[i].getValue());
			}
		}
		//如果其中有一项不满足，就返回登录页面
		if(u.getUsername() == null || u.getPassword() == null || u.getUsername().equals("") || u.getPassword().equals("")) {
			request.getRequestDispatcher("/user/login").forward(request, response);
			return false;
		}
		//利用cookie中的信息进行免登录
		User login = service.login(u);
		if(login != null) {
			request.getSession().setAttribute(CmsContant.USER_KEY, login);
			return true;
		}
		request.getRequestDispatcher("/user/login").forward(request, response);
		return false;
	}
}
