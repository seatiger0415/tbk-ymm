package com.tbk.ymm.web.controllers;

import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;

import com.tbk.ymm.commons.dto.YmmCateBarDTO;
import com.tbk.ymm.commons.model.YmmNavigationCate;
import com.tbk.ymm.commons.model.article.YmmArticleCate;
import com.tbk.ymm.service.YmmArticleCateService;
import com.tbk.ymm.service.YmmArticleService;
import com.tbk.ymm.service.YmmCateService;

/**
 * 网站地图页面
 * 
 * @author David (qidawei@xiaomi.com)
 */
@Path("/siteMap")
public class YmmSiteMapController {

	@Autowired
	private YmmCateService ymmCateService;
	@Autowired
	private YmmArticleCateService ymmArticleCateService;
	@Autowired
	private YmmArticleService ymmArticleService;

	/**
	 * 各个分类的页面
	 */
	@Get("")
	public String siteMap(Invocation inv) {
		// 1.导航列表
		List<YmmNavigationCate> navigationList = ymmCateService.getNavigationCateList();
		// 2.所有二级类目列表
		List<YmmCateBarDTO> allCateDTOList = ymmCateService.getAllCate(navigationList);
		// 3.所有文章类目列表
		List<YmmArticleCate> shoppingArticleCateList = ymmArticleCateService.getAllShoppingArticleCateList();
		List<YmmArticleCate> experienceArticleCateLis = ymmArticleCateService.getAllExperienceArticleCateList();
		// 4.build入所有文章title列表，按文章分类聚类
		ymmArticleService.buildAllArticleTileIntoCateList(shoppingArticleCateList);
		ymmArticleService.buildAllArticleTileIntoCateList(experienceArticleCateLis);
		//
		//
		inv.addModel("navigationList", navigationList);
		inv.addModel("allCateDTOList", allCateDTOList);
		inv.addModel("shoppingArticleCateList", shoppingArticleCateList);
		inv.addModel("experienceArticleCateLis", experienceArticleCateLis);
		//
		return "ymm_site_map";
	}
}
