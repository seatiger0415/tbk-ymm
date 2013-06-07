package com.tbk.ymm.commons.enums;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tbk.ymm.commons.dto.YmmItem;
import com.tbk.ymm.data.catcher.commons.model.YmmFavoriteItem;
import com.tbk.ymm.data.catcher.utils.RoseBeanFactory;
import com.tbk.ymm.logic.YmmFavoriteItemLogic;
import com.tbk.ymm.logic.impl.YmmFavoriteItemLogicImpl;

/**
 * 数据源的定义；这个类可能会膨胀的比较大，但这种设计方式还是比较舒服的
 * 
 * @author David (qidawei@xiaomi.com)
 */
public enum ItemDataSource {
	tbk_item(1) {

		@Override
		public List<YmmItem> getItemList(List<String> itemIdList) {
			// TODO
			return null;
		}
	}, //
	favorite_item(2) {

		@Override
		public List<YmmItem> getItemList(List<String> itemIdList) {
			YmmFavoriteItemLogic ymmFavoriteItemLogic = RoseBeanFactory.getBean(YmmFavoriteItemLogicImpl.class);
			List<YmmFavoriteItem> favItemList = ymmFavoriteItemLogic.getByTrackIidList(itemIdList);
			List<YmmItem> itemList = Lists.newArrayListWithExpectedSize(favItemList.size());
			for (YmmFavoriteItem ymmFavoriteItem : favItemList) {
				itemList.add(YmmItem.getFromYmmFavoriteItem(ymmFavoriteItem));
			}
			return itemList;
		}
	} //

	;

	/**
	 * 各个数据源根据itemId list获取商品；因为itemId可能是trackIid 或 numIid列表，所以统一用List<String>;
	 * 
	 * @param itemIdList
	 *            对不同的数据源，含义不同，可能是trackIid 或 numIid列表
	 * @return
	 */
	public abstract List<YmmItem> getItemList(List<String> itemIdList);

	//
	private static final HashMap<Integer, ItemDataSource> allMap = Maps.newHashMap();
	static {
		for (ItemDataSource itemDataSource : ItemDataSource.values()) {
			allMap.put(itemDataSource.getId(), itemDataSource);
		}
	}

	private int id;

	private ItemDataSource(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static ItemDataSource getItemDataSourceById(int id) {
		return allMap.get(id);
	}

}
