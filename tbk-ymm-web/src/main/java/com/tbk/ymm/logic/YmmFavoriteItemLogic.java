package com.tbk.ymm.logic;

import java.util.List;

import com.tbk.ymm.data.catcher.commons.model.YmmFavoriteItem;

/**
 * 对“商品关联推荐获取的商品信息“的操作逻辑
 * 
 * @author David (qidawei@xiaomi.com)
 */
public interface YmmFavoriteItemLogic {

	/**
	 * @param YmmFavoriteItemTable
	 * @param offset
	 * @param num
	 * @return
	 */
	public List<YmmFavoriteItem> getByCidListInSellCountOrder(List<Long> cidList, int offset, int num);

	/**
	 * 根据trackIidList获取商品列表
	 * 
	 * @param trackIidList
	 * @return
	 */
	public List<YmmFavoriteItem> getByTrackIidList(List<String> trackIidList);

	/**
	 * 获取一个类别下商品的总数
	 * 
	 * @param cidList
	 * @return
	 */
	public int getCountByCidList(List<Long> cidList);
}
