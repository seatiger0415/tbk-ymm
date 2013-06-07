package com.tbk.ymm.web.controllers;

import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.tbk.ymm.commons.dto.ResultView;
import com.tbk.ymm.commons.dto.YmmCateBarDTO;
import com.tbk.ymm.commons.dto.YmmItem;
import com.tbk.ymm.commons.dto.params.YmmItemQueryParam;
import com.tbk.ymm.data.catcher.commons.model.YmmItemCate;
import com.tbk.ymm.service.YmmCateService;
import com.tbk.ymm.service.YmmItemsService;

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

	/**
	 * 各个分类的页面
	 */
	@Get("/{cateId:[0-9]+}")
	public String catePage(Invocation inv, @Param("cateId") final int cateId,
			@Param("curPage") final int curPage
			) {
		YmmCateBarDTO ymmCateBarDTO = ymmCateService.getCateBar(cateId);
		ResultView<YmmItem> itemResultView = getItemsResultView(ymmCateBarDTO, curPage);
		//
		// 一级导航
		inv.addModel("navigationList", ymmCateBarDTO.getNavigationList());
		// 二级导航
		inv.addModel("subCateList", ymmCateBarDTO.getLv2CateList());// 可以看需要适当加上一级分类
		//
		inv.addModel("itemResultView", itemResultView);
		//
		inv.addModel("curCid", cateId);
		//
		return "ymm_cate";
	}

	// ----------------------------------------------------------------------

	/**
	 * @param ymmCateBarDTO
	 * @param curPage
	 * @return
	 */
	private ResultView<YmmItem> getItemsResultView(YmmCateBarDTO ymmCateBarDTO, int curPage) {
		ResultView<YmmItem> itemResultView;
		switch (ymmCateBarDTO.getInputCateType()) {
			case NAVIGATION :
			case LV1 : // 这里一般没有入口，进不来;目前LV1 Cid进来直接提升为导航id
				List<YmmItemCate> lv2CateList = ymmCateBarDTO.getLv2CateList();
				List<Long> lv2CateIdList = refactorCidList(lv2CateList);
				logger.debug("YmmCateController.cate is nav or lv1.lv2CateIdList:" + lv2CateIdList);
				itemResultView = getItemsByLv2IdList(lv2CateIdList, curPage);
				break;
			case LV2 :
				itemResultView = getItemsByLv2IdList(Lists.newArrayList(ymmCateBarDTO.getInputCateId()), curPage);
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
	private ResultView<YmmItem> getItemsByLv2IdList(List<Long> lv2CidList, int curPage) {
		YmmItemQueryParam param = new YmmItemQueryParam();
		param.setCidList(lv2CidList);
		param.setCurPage(curPage);
		param.setPageSize(PAGE_SIZE);
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
