package com.tbk.ymm.web.controllers.article;

import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;

import com.tbk.ymm.commons.consts.YmmConsts;
import com.tbk.ymm.commons.dto.PageMeta;
import com.tbk.ymm.commons.model.YmmNavigationCate;
import com.tbk.ymm.commons.model.article.YmmArticle;
import com.tbk.ymm.commons.model.article.YmmArticleCate;
import com.tbk.ymm.service.YmmArticleCateService;
import com.tbk.ymm.service.YmmArticleService;
import com.tbk.ymm.service.YmmCateService;
import com.tbk.ymm.utils.PageMetaUtil;
import com.tbk.ymm.utils.cate.YmmCateUtil;

@Path("/cate")
public class YmmArticleCateController {

	@Autowired
	private YmmCateService ymmCateService;
	@Autowired
	private YmmArticleCateService ymmArticleCateService;
	@Autowired
	private YmmArticleService ymmArticleService;

	@Get("/shopping")
	public String shoppingPage(Invocation inv) {
		return articleCatePage(inv, YmmConsts.SHOPPING_CATE_ID);
	}

	@Get("/experience")
	public String experiencePage(Invocation inv) {
		return articleCatePage(inv, YmmConsts.EXPERIENCE_CATE_ID);
	}

	@Get("/{articleCateId:[0-9]+}")
	public String articleCatePage(Invocation inv, @Param("articleCateId") int articleCateId) {
		int lv2CateId = YmmCateUtil.getLv2ArticleCateId(articleCateId);
		//
		List<YmmNavigationCate> navigationList = ymmCateService.getNavigationCateList();
		//
		List<YmmArticleCate> articleCateList = ymmArticleCateService.getArtileCateList(lv2CateId);
		List<YmmArticle> articleList = ymmArticleService.getListByArticleCateId(lv2CateId);
		//
		YmmArticleCate curArticleCate = getCurArticleCate(lv2CateId, articleCateList);
		PageMeta curMeta = PageMetaUtil.getCurMetaForArticleCatePage(curArticleCate);
		//
		inv.addModel("curMeta", curMeta);
		inv.addModel("isHome", false);
		inv.addModel("navigationList", navigationList);
		inv.addModel("articleCateList", articleCateList);
		inv.addModel("articleList", articleList);
		inv.addModel("curArticleCateId", lv2CateId);
		//
		return "ymm_article_cate";
	}

	// -----------------------------------------------

	private YmmArticleCate getCurArticleCate(int lv2CateId, List<YmmArticleCate> articleCateList) {
		for (YmmArticleCate ymmArticleCate : articleCateList) {
			if (lv2CateId == ymmArticleCate.getId()) {
				return ymmArticleCate;
			}
		}
		return null;
	}

}
