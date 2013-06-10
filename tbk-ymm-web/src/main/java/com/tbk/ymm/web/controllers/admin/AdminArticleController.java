package com.tbk.ymm.web.controllers.admin;

import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.tbk.ymm.commons.consts.YmmConsts;
import com.tbk.ymm.commons.model.YmmNavigationCate;
import com.tbk.ymm.commons.model.article.YmmArticle;
import com.tbk.ymm.commons.model.article.YmmArticleCate;
import com.tbk.ymm.dao.article.YmmArticleDAO;
import com.tbk.ymm.service.YmmArticleCateService;
import com.tbk.ymm.service.YmmArticleService;
import com.tbk.ymm.service.YmmCateService;
import com.tbk.ymm.utils.ArticleUtil;
import com.tbk.ymm.utils.cate.YmmCateUtil;

@Path("/article")
public class AdminArticleController {

	@Autowired
	private YmmCateService ymmCateService;
	@Autowired
	private YmmArticleCateService ymmArticleCateService;
	@Autowired
	private YmmArticleDAO ymmArticleDAO;
	@Autowired
	private YmmArticleService ymmArticleService;

	@Get("/cate/{articleCateId:[0-9]+}")
	public String catePage(Invocation inv, @Param("articleCateId") int articleCateId) {
		List<YmmNavigationCate> navigationList = ymmCateService.getNavigationCateList();
		//
		int lv2CateId = YmmCateUtil.getLv2ArticleCateId(articleCateId);
		//
		List<YmmArticleCate> articleCateList = ymmArticleCateService.getArtileCateList(lv2CateId);
		List<YmmArticle> articleList = ymmArticleDAO.getListByCidAdmin(articleCateId);
		//
		inv.addModel("isHome", false);
		inv.addModel("navigationList", navigationList);
		inv.addModel("articleCateList", articleCateList);
		inv.addModel("curArticleCateId", lv2CateId);
		inv.addModel("articleList", articleList);
		//
		return "admin_article_cate";
	}

	@Get("/{articleId:[0-9]+}")
	public String articlePage(Invocation inv, @Param("articleId") int articleId) {
		List<YmmNavigationCate> navigationList = ymmCateService.getNavigationCateList();
		YmmArticle article = ymmArticleDAO.getByIdAdmin(articleId);
		int cid = null == article ? YmmConsts.SHOPPING_CATE_ID : article.getArticleCateId();
		List<YmmArticleCate> articleCateList = ymmArticleCateService.getArtileCateList(cid);
		//
		inv.addModel("article", article);
		inv.addModel("isHome", false);
		inv.addModel("articleCateList", articleCateList);
		inv.addModel("navigationList", navigationList);
		//
		return "admin_article";
	}

	@Post("/update")
	public String updateOrNew(Invocation inv, @Param("article") YmmArticle article) {
		//
		ArticleUtil.replaceWithHtmlSymbol(article);
		//
		if (0 == article.getId()) {
			ymmArticleDAO.insert(article);
		} else {
			ymmArticleDAO.insertOrUpdate(article);
		}
		//
		return "r:/admin/article/" + article.getId();
	}

	@Post("/remove")
	public String removeArticle(Invocation inv, @Param("id") int id) {
		//
		if (id <= 0) {
			return "@fail";
		}
		//
		ymmArticleDAO.remove(id);
		//
		return "@ok";
	}

}
