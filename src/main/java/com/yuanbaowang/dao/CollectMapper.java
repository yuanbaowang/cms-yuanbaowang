/**
 * 
 */
package com.yuanbaowang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yuanbaowang.bean.Collect;

/**
 * @author 袁保旺
 *
 * 2020年2月18日 上午9:41:30 
 */
public interface CollectMapper {

	/**
	 *	添加
	 */
	@Insert("INSERT cms_scj SET text = #{c.text},url = #{c.url},user_id = #{c.user_id},created = now()")
	int addCollect(@Param("c")Collect collect);

	/**
	 * @param id 
	 * 	查询
	 *
	 */
	@Select("SELECT * FROM cms_scj WHERE user_id = #{id}")
	List<Collect> selCollect(Integer id);

	/**
	 *	删除
	 * @param id2
	 */
	@Delete("DELETE FROM cms_scj WHERE user_id = #{userid} AND id = #{id}")
	int delCollect(@Param("userid")Integer userId, @Param("id")Integer id2);

}
