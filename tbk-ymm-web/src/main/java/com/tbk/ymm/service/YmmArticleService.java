package com.tbk.ymm.service;

import java.util.List;

import com.tbk.ymm.commons.model.article.YmmArticle;

/**
 * @author David (qidawei@xiaomi.com)
 */
public interface YmmArticleService {

	/**
	 * 
	 * @return
	 */
	public YmmArticle getById(int id);
	
	/**
	 * @param articleCateId
	 * @return
	 */
	public List<YmmArticle> getListByArticleCateId(int articleCateId);

}
