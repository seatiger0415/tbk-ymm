package com.tbk.ymm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tbk.ymm.commons.model.article.YmmArticle;
import com.tbk.ymm.commons.model.article.YmmArticleCate;
import com.tbk.ymm.dao.article.YmmArticleDAO;
import com.tbk.ymm.service.YmmArticleService;

@Service
public class YmmArticleServiceImpl implements YmmArticleService {

	@Autowired
	private YmmArticleDAO ymmArticleDAO;

	@Override
	public YmmArticle getById(int id) {
		// TODO cache
		return ymmArticleDAO.getById(id);
	}

	@Override
	public List<YmmArticle> getListByArticleCateId(int articleCateId) {
		// TODO cache
		return ymmArticleDAO.getListByCid(articleCateId, 1000);
	}

	@Override
	public void buildAllArticleTileIntoCateList(List<YmmArticleCate> articleCateList) {
		List<Integer> articleCateIdList = getArticleCateIdList(articleCateList);
		//
		List<YmmArticle> articleList = ymmArticleDAO.getTitleListByCateIdList(articleCateIdList);
		Map<Integer, List<YmmArticle>> articleMap = buildArticleMapByCateId(articleList);
		for (YmmArticleCate ymmArticleCate : articleCateList) {
			List<YmmArticle> curArticleList = articleMap.get(ymmArticleCate.getId());
			ymmArticleCate.buildArticleList(curArticleList);
		}
	}

	// -----------------------------

	private Map<Integer, List<YmmArticle>> buildArticleMapByCateId(List<YmmArticle> articleList) {
		Map<Integer, List<YmmArticle>> articleMap = Maps.newHashMap();
		for (YmmArticle ymmArticle : articleList) {
			Integer articleCateId = ymmArticle.getArticleCateId();
			List<YmmArticle> innerArticleList = articleMap.get(articleCateId);
			if (null == innerArticleList) {
				innerArticleList = Lists.newArrayList();
				articleMap.put(articleCateId, innerArticleList);
			}
			innerArticleList.add(ymmArticle);
		}
		return articleMap;
	}

	private List<Integer> getArticleCateIdList(List<YmmArticleCate> articleCateList) {
		List<Integer> articleCateIdList = Lists.newArrayList();
		for (YmmArticleCate ymmArticleCate : articleCateList) {
			articleCateIdList.add(ymmArticleCate.getId());
		}
		return articleCateIdList;
	}

}
