package com.tbk.ymm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tbk.ymm.commons.model.article.YmmArticle;
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

}
