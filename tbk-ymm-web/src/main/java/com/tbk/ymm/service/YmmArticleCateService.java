package com.tbk.ymm.service;

import java.util.List;

import com.tbk.ymm.commons.model.article.YmmArticleCate;

/**
 * @author David (qidawei@xiaomi.com)
 */
public interface YmmArticleCateService {

	/**
	 * 购物攻略类目列表
	 * 
	 * @return
	 */
	public List<YmmArticleCate> getArtileCateList(int curId);

	/**
	 * 获取所有购物导购攻略的类目列表
	 * 
	 * @return
	 */
	public List<YmmArticleCate> getAllShoppingArticleCateList();

	/**
	 * 获取所有孕妈妈经验谈的类目列表
	 * 
	 * @return
	 */
	public List<YmmArticleCate> getAllExperienceArticleCateList();

}
