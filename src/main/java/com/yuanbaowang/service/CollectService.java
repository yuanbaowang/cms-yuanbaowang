/**
 * 
 */
package com.yuanbaowang.service;

import com.github.pagehelper.PageInfo;
import com.yuanbaowang.bean.Collect;

/**
 * @author 袁保旺
 *
 * 2020年2月18日 上午9:41:51 
 */
public interface CollectService {

	/**
	 *
	 */
	int addCollect(Collect collect);

	/**
	 * @param pageNum 
	 *
	 */
	PageInfo<Collect> selCollect(Integer id, Integer pageNum);

	/**
	 * @param id2 
	 *
	 */
	int delCollect(Integer id, Integer id2);

}
