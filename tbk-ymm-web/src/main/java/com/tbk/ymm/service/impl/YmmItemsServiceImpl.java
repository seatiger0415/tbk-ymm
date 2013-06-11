package com.tbk.ymm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.tbk.ymm.commons.dto.ResultView;
import com.tbk.ymm.commons.dto.YmmItem;
import com.tbk.ymm.commons.dto.params.YmmItemQueryParam;
import com.tbk.ymm.dao.YmmFavoriteItemDAO;
import com.tbk.ymm.dao.cate.YmmItemCateDAO;
import com.tbk.ymm.data.catcher.commons.model.YmmFavoriteItem;
import com.tbk.ymm.logic.YmmFavoriteItemLogic;
import com.tbk.ymm.service.YmmItemsService;

@Service
public class YmmItemsServiceImpl implements YmmItemsService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private YmmFavoriteItemLogic ymmFavoriteItemLogic;
	@Autowired
	private YmmFavoriteItemDAO ymmFavoriteItemDAO;
	@Autowired
	private YmmItemCateDAO ymmItemCateDAO;

	@Override
	public ResultView<YmmItem> getByLv2CidListPagedInOrder(YmmItemQueryParam param) {
		ResultView<YmmItem> resultView = ResultView.newResultView();
		if (!checkParam(param)) {
			return resultView;
		}
		//
		List<Long> cidList = param.getCidList();
		int curPage = param.getCurPage();
		int pageSize = param.getPageSize();
		int smallPrice = param.getSmallPrice();
		int bigPrice = param.getBigPrice();
		List<YmmFavoriteItem> favItemList = ymmFavoriteItemLogic.getByCidListAndPrice(cidList, smallPrice, bigPrice,
				curPage * pageSize, pageSize);
		List<YmmItem> itemList = buildItemList(favItemList);
		// TODO cache
		int count = ymmFavoriteItemLogic.getCountByCidListAndPrice(cidList, smallPrice, bigPrice);
		resultView.setCurPage(curPage);
		resultView.setPageSize(pageSize);
		resultView.setCountAll(count);
		resultView.setList(itemList);
		//
		resultView.buildPageResult();
		//
		logger.debug("YmmItemsServiceImpl.resultView:" + resultView);
		//
		return resultView;
	}

	// ----------------------------------------------

	private List<YmmItem> buildItemList(List<YmmFavoriteItem> favItemList) {
		List<YmmItem> itemList = Lists.newArrayListWithExpectedSize(favItemList.size());
		for (YmmFavoriteItem favItem : favItemList) {
			itemList.add(YmmItem.getFromYmmFavoriteItem(favItem));
		}
		return itemList;
	}

	private boolean checkParam(YmmItemQueryParam param) {
		// TODO Auto-generated method stub
		return true;
	}

}
