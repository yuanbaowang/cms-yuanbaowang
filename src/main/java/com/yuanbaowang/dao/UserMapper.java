/**
 * 
 */
package com.yuanbaowang.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yuanbaowang.bean.User;

/**
 * @author 袁保旺
 *
 * 2019年12月10日 上午11:01:16 
 */
public interface UserMapper {

	/**
	 *	根据注册用户名 判断是否唯一
	 */
	@Select("select id,username,password from cms_user where username = #{username}")
	User getUserByUsername(@Param("username")String username);

	/**
	 * 	进行注册
	 */
	@Insert("insert cms_user set username = #{u.username},password = #{u.password},locked = 0,create_time = now(),score = 0,role = 0 ")
	int register(@Param("u")User u);

	/**
	 *	进行登录操作
	 *	根据用户名和密码来查询用户信息
	 */
	@Select("select id,username,password,nickname,birthday,gender,locked,create_time,update_time,url,score,role from cms_user where username = #{u.username} and password = #{u.password} limit 1")
	User login(@Param("u")User u);

}
