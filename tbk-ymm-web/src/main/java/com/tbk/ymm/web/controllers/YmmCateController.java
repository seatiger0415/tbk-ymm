package com.tbk.ymm.web.controllers;

import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.DefValue;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.tbk.ymm.commons.dto.PageMeta;
import com.tbk.ymm.commons.dto.ResultView;
import com.tbk.ymm.commons.dto.YmmCateBarDTO;
import com.tbk.ymm.commons.dto.YmmItem;
import com.tbk.ymm.commons.dto.params.CatePageParam;
import com.tbk.ymm.commons.dto.params.YmmItemQueryParam;
import com.tbk.ymm.commons.model.YmmNavigationCate;
import com.tbk.ymm.commons.model.article.YmmArticle;
import com.tbk.ymm.commons.model.article.YmmArticleCate;
import com.tbk.ymm.data.catcher.commons.model.YmmItemCate;
import com.tbk.ymm.service.YmmArticleCateService;
import com.tbk.ymm.service.YmmArticleService;
import com.tbk.ymm.service.YmmCateRefService;
import com.tbk.ymm.service.YmmCateService;
import com.tbk.ymm.service.YmmItemsService;
import com.tbk.ymm.utils.PageMetaUtil;

/**
 * 某个具体的分类页面
 * 
 * @author David (qidawei@xiaomi.com)
 */
@Path("/cate")
public class YmmCateController {

	private static final int PAGE_SIZE = 40;

	private static final Logger logger = LoggerFactory.getLogger(YmmCateController.class);

	@Autowired
	private YmmCateService ymmCateService;
	@Autowired
	private YmmItemsService ymmItemsService;
	@Autowired
	private YmmArticleService ymmArticleService;
	@Autowired
	private YmmCateRefService ymmCateRefService;
	@Autowired
	private YmmArticleCateService ymmArticleCateService;

	/**
	 * 各个分类的页面
	 */
	@Get("/{cateId:[0-9]+}")
	public String catePage(Invocation inv, @Param("cateId") final int cateId,
			@Param("curPage") final int curPage,
			@Param("smallPrice") final int smallPrice,
			@Param("bigPrice") final int bigPrice) {
		YmmCateBarDTO ymmCateBarDTO = ymmCateService.getCateBar(cateId);
		//
		CatePageParam catePageParam = buildCatePageParam(cateId, curPage, smallPrice, bigPrice);
		ResultView<YmmItem> itemResultView = getItemsResultView(ymmCateBarDTO, catePageParam);
		// 网站的title, keywords, description
		inv.addModel("curMeta", PageMetaUtil.getCurMetaForCatePage(ymmCateBarDTO));
		// 一级导航
		inv.addModel("navigationList", ymmCateBarDTO.getNavigationList());
		// 二级导航
		inv.addModel("subCateList", ymmCateBarDTO.getLv2CateList());// 可以看需要适当加上一级分类
		//
		inv.addModel("itemResultView", itemResultView);
		inv.addModel("catePageParam", catePageParam);
		//
		inv.addModel("curCid", cateId);
		inv.addModel("isItemPage", true);// 区分攻略子页面和商品子页面
		//
		return "ymm_cate";
	}

	/**
	 * 各个分类的页面的购物攻略子页面
	 */
	@Get("/{cateId:[0-9]+}/article")
	public String articleOfCatePage(Invocation inv, @Param("cateId") final int cateId,
			@Param("article") @DefValue("") final String article) {
		YmmCateBarDTO ymmCateBarDTO = ymmCateService.getCateBar(cateId);
		//
		// TODO 目前购物攻略只关联到了导航级别的类目，将来可能需要关联到各个子类目
		long navCateId = getNavCateId(ymmCateBarDTO);
		int articleCateId = ymmCateRefService.getArticleCateIdByCateId(navCateId);
		//
		List<YmmArticle> articleList = ymmArticleService.getListByArticleCateId(articleCateId);
		//
		YmmArticleCate curArticleCate = ymmArticleCateService.getById(articleCateId);
		PageMeta curMeta = PageMetaUtil.getCurMetaForArticleCatePage(curArticleCate);
		//
		inv.addModel("curMeta", curMeta);
		// 一级导航
		inv.addModel("navigationList", ymmCateBarDTO.getNavigationList());
		// 二级导航
		inv.addModel("subCateList", ymmCateBarDTO.getLv2CateList());// 可以看需要适当加上一级分类
		//
		inv.addModel("articleList", articleList);
		//
		inv.addModel("curCid", cateId);
		inv.addModel("isItemPage", false);// 当前是攻略子页面
		//
		return "ymm_cate_article";
	}

	// ----------------------------------------------------------------------

	/**
	 * 
	 * @return
	 */
	private CatePageParam buildCatePageParam(int cateId, int curPage, int smallPrice, int bigPrice) {
		CatePageParam catePageParam = new CatePageParam();
		catePageParam.setCateId(cateId);
		catePageParam.setCurPage(curPage);
		catePageParam.setSmallPrice(smallPrice);
		catePageParam.setBigPrice(bigPrice);
		return catePageParam;
	}

	/**
	 * @param ymmCateBarDTO
	 * @return
	 */
	private long getNavCateId(YmmCateBarDTO ymmCateBarDTO) {
		for (YmmNavigationCate navCate : ymmCateBarDTO.getNavigationList()) {
			if (navCate.selected()) {
				return navCate.getId();
			}
		}
		return 0;
	}

	/**
	 * @param ymmCateBarDTO
	 * @param curPage
	 * @return
	 */
	private ResultView<YmmItem> getItemsResultView(YmmCateBarDTO ymmCateBarDTO, CatePageParam catePageParam) {
		ResultView<YmmItem> itemResultView;
		switch (ymmCateBarDTO.getInputCateType()) {
			case NAVIGATION :
			case LV1 : // 这里一般没有入口，进不来;目前LV1 Cid进来直接提升为导航id
				List<YmmItemCate> lv2CateList = ymmCateBarDTO.getLv2CateList();
				List<Long> lv2CateIdList = refactorCidList(lv2CateList);
				logger.debug("YmmCateController.cate is nav or lv1.lv2CateIdList:" + lv2CateIdList);
				itemResultView = getItemsByLv2IdList(lv2CateIdList, catePageParam);
				break;
			case LV2 :
				itemResultView = getItemsByLv2IdList(Lists.newArrayList(ymmCateBarDTO.getInputCateId()), catePageParam);
				break;
			default :
				itemResultView = ResultView.emptyResultView();
		}
		return itemResultView;
	}

	/**
	 * @param lv2CidList
	 * @return
	 */
	private ResultView<YmmItem> getItemsByLv2IdList(List<Long> lv2CidList, CatePageParam catePageParam) {
		YmmItemQueryParam param = new YmmItemQueryParam();
		param.setCidList(lv2CidList);
		param.setCurPage(catePageParam.getCurPage());
		param.setPageSize(PAGE_SIZE);
		param.setSmallPrice(catePageParam.getSmallPrice());
		param.setBigPrice(catePageParam.getBigPrice());
		return ymmItemsService.getByLv2CidListPagedInOrder(param);
	}

	/**
	 * @param lv2CateList
	 * @return
	 */
	private List<Long> refactorCidList(List<YmmItemCate> lv2CateList) {
		List<Long> idList = Lists.newArrayListWithExpectedSize(lv2CateList.size());
		for (YmmItemCate ymmItemCate : lv2CateList) {
			if (null != ymmItemCate) {
				idList.add(ymmItemCate.getCid());
			}
		}
		return idList;
	}

}
