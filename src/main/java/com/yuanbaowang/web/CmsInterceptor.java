/**
 * 
 */
package com.yuanbaowang.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.yuanbaowang.bean.User;
import com.yuanbaowang.common.CmsContant;

/**
 * @author 袁保旺
 *
 * 2019年12月11日 下午8:49:23 
 * 	配置拦截器
 */
public class CmsInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User user = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		//如果为空 就进行拦截 直接返回false 转发到登录页面
		if(user == null) {
			request.getRequestDispatcher("/user/login").forward(request, response);
			return false;
		}
		//放行
		return true;
	}
}
