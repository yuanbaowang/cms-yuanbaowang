/**
 * 
 */
package com.yuanbaowang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanbaowang.bean.Collect;
import com.yuanbaowang.common.CmsContant;
import com.yuanbaowang.dao.CollectMapper;
import com.yuanbaowang.service.CollectService;

import yuanbaowang_cms_utils.StringUtils;

/**
 * @author 袁保旺
 *
 * 2020年2月18日 上午9:42:14 
 */
@Service
public class CollectServiceImp implements CollectService {
	@Autowired
	CollectMapper mapper;

	/**
	 * 	添加收藏夹
	 */
	@Override
	public int addCollect(Collect collect) {
		//判断是否为合法的uRl
		boolean bool = StringUtils.isUrl(collect.getUrl());
		if(bool) {
			return mapper.addCollect(collect);
		}
		return 0;
		
	}

	/**
	 * 	查询收藏夹 
	 */
	@Override
	public PageInfo<Collect> selCollect(Integer id,Integer pageNum) {
		PageHelper.startPage(pageNum,CmsContant.PAGESIZE);
		return new PageInfo<Collect>(mapper.selCollect(id));
	}

	/**
	 * 	删除
	 */
	@Override
	public int delCollect(Integer userid,Integer id) {
		// TODO Auto-generated method stub
		return mapper.delCollect(userid,id);
	}

}
