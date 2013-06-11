package com.tbk.ymm.logic.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.tbk.ymm.commons.dto.YmmItem;
import com.tbk.ymm.commons.enums.ItemDataSource;
import com.tbk.ymm.dao.cate.YmmItemCateDAO;
import com.tbk.ymm.data.catcher.commons.model.YmmFavoriteItem;
import com.tbk.ymm.data.catcher.commons.model.YmmItemCate;
import com.tbk.ymm.logic.YmmFavoriteItemLogic;
import com.tbk.ymm.logic.YmmItemLogic;
import com.tbk.ymm.utils.collections.CollectionUtils;

/**
 * @author David (qidawei@xiaomi.com)
 */
@Service
public class YmmItemLogicImpl implements YmmItemLogic {

	@Autowired
	private YmmFavoriteItemLogic ymmFavoriteItemLogic;
	@Autowired
	private YmmItemCateDAO ymmItemCateDAO;

	@Override
	public List<YmmItem> getItemList(ItemDataSource itemDataSource, List<String> itemIdList) {
		if (null == itemDataSource || CollectionUtils.isCollectionEmpty(itemIdList)) {
			return Collections.emptyList();
		}
		//
		return itemDataSource.getItemList(itemIdList);
	}

	@Override
	public List<YmmItem> getByCidListInSellCountOrder(List<Long> lv2CateIdList, long startItemId, int num) {
		// TODO 目前只有favorite item
		List<YmmFavoriteItem> favItemList = ymmFavoriteItemLogic.getByCidListAndPrice(lv2CateIdList, 0, 0, 0, num);
		List<YmmItem> itemList = Lists.newArrayListWithExpectedSize(favItemList.size());
		// 这里需要把分类信息build进每个商品
		Map<Long, YmmItemCate> allLv2CateMap = ymmItemCateDAO.getAllLv2CateMap();
		for (YmmFavoriteItem favItem : favItemList) {
			YmmItem ymmItem = YmmItem.getFromYmmFavoriteItem(favItem);
			//
			ymmItem.buildItemCate(allLv2CateMap.get(favItem.getCid()));
			//
			itemList.add(ymmItem);
		}
		return itemList;
	}
}
