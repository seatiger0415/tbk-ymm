package com.tbk.ymm.service;

import java.util.List;

import com.tbk.ymm.commons.model.article.YmmArticle;
import com.tbk.ymm.commons.model.article.YmmArticleCate;

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

	/**
	 * 把一个类目下的所有文章的标题和id信息放到文章类目模型中
	 * 
	 * @param @param articleCateList
	 * @return
	 */
	public void buildAllArticleTileIntoCateList(List<YmmArticleCate> articleCateList);

}
