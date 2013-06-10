package com.tbk.ymm.web.controllers.article;

import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;

import com.tbk.ymm.commons.consts.YmmConsts;
import com.tbk.ymm.commons.model.YmmNavigationCate;
import com.tbk.ymm.commons.model.article.YmmArticle;
import com.tbk.ymm.commons.model.article.YmmArticleCate;
import com.tbk.ymm.service.YmmArticleCateService;
import com.tbk.ymm.service.YmmArticleService;
import com.tbk.ymm.service.YmmCateService;

@Path("/")
public class YmmArticleController {

	@Autowired
	private YmmCateService ymmCateService;
	@Autowired
	private YmmArticleCateService ymmArticleCateService;
	@Autowired
	private YmmArticleService ymmArticleService;

	@Get("/{articleId:[0-9]+}")
	public String articlePage(Invocation inv, @Param("articleId") int articleId) {
		List<YmmNavigationCate> navigationList = ymmCateService.getNavigationCateList();
		YmmArticle article = ymmArticleService.getById(articleId);
		int cid = null == article ? YmmConsts.SHOPPING_CATE_ID : article.getArticleCateId();
		List<YmmArticleCate> articleCateList = ymmArticleCateService.getArtileCateList(cid);
		//
		inv.addModel("isHome", false);
		inv.addModel("navigationList", navigationList);
		inv.addModel("articleCateList", articleCateList);
		inv.addModel("article", article);
		inv.addModel("curArticleCateId", cid);
		//
		return "ymm_article";
	}

}
