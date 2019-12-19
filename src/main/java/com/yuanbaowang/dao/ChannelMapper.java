/**
 * 
 */
package com.yuanbaowang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yuanbaowang.bean.Channel;
import com.yuanbaowang.bean.Slide;

/**
 * @author 袁保旺
 *
 * 2019年12月18日 下午6:44:51 
 */
public interface ChannelMapper {

	/**
	 *	获取所有栏目
	 */
	@Select("SELECT id,name FROM cms_channel")
	List<Channel> getChannels();

	

}
