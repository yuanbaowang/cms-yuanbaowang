/**
 * 
 */
package com.yuanbaowang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yuanbaowang.bean.Slide;

/**
 * @author 袁保旺
 *
 * 2019年12月18日 下午6:44:51 
 */
public interface SlideMapper {

	/**
	 *	获取所有轮播图
	 */
	@Select("SELECT id,title,picture,url FROM cms_slide ORDER BY id")
	List<Slide> slideList();

}
