package com.tbk.ymm.logic;

import java.util.List;

import com.tbk.ymm.commons.dto.YmmItem;
import com.tbk.ymm.commons.enums.ItemDataSource;

/**
 * 在各个数据源之上的商品logic
 * 
 * @author David (qidawei@xiaomi.com)
 */
public interface YmmItemLogic {

	/**
	 * @param itemDataSource
	 * @param itemIdlist
	 *            可能是trackIid 或 numIid列表
	 * @return
	 */
	public List<YmmItem> getItemList(ItemDataSource itemDataSource, List<String> itemIdlist);

	/**
	 * 根据cidList获取指定数量的YmmItem列表，按SellCount降序
	 * 
	 * @param lv2CateIdList
	 * @param startItemId
	 * @param num
	 * @return
	 */
	public List<YmmItem> getByCidListInSellCountOrder(List<Long> lv2CateIdList, long startItemId, int num);

}
