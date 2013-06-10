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

}
