package com.tbk.ymm.commons.model;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;
import com.tbk.ymm.commons.enums.ItemDataSource;

/**
 * 每个导航类下面的推荐商品或者推荐的二级类目；<br/>
 * <br/>
 * 优先级： 如果配置了推荐商品，则取商品展示，否则取配置的二级类目下的排名靠前的商品展示。
 * 
 * @author David (qidawei@xiaomi.com)
 */
public class YmmRecommendEachNavigation {

	public static final String TABLE = "ymm_recommend_each_navigation";
	public static final int NUM_EACH_LV2CATE = 2; // 推荐中，每个二级类目最多取多少个商品

	private int id;
	private long navigationId;
	private String lv2CidsStr; // 推荐的二级类目id，用逗号隔开
	private String itemIdsStr; // 推荐的商品id(可能是trackIid或者是numIid)，用逗号隔开
	private int itemDataSourceId; // 推荐的商品属于哪个数据源(一条配置智能配一种数据源，想要配置多个数据源的商品来推荐，就插入多条配置吧)

	/**
	 * @return
	 */
	public List<Long> getLv2CidList() {
		if (StringUtils.isEmpty(lv2CidsStr)) {
			return Collections.emptyList();
		}
		String[] cidArr = lv2CidsStr.split(",");
		List<Long> ret = Lists.newArrayList();
		for (String cid : cidArr) {
			if (StringUtils.isEmpty(cid) || !StringUtils.isNumeric(cid)) {
				continue;
			}
			ret.add(Long.parseLong(StringUtils.trim(cid)));
		}
		return ret;
	}

	/**
	 * @return
	 */
	public List<String> getItemIdList() {
		if (StringUtils.isEmpty(itemIdsStr)) {
			return Collections.emptyList();
		}
		String[] itemIdArr = itemIdsStr.split(",");
		List<String> ret = Lists.newArrayList();
		for (String itemId : itemIdArr) {
			if (StringUtils.isEmpty(itemId)) {
				continue;
			}
			ret.add(StringUtils.trim(itemId));
		}
		return ret;
	}

	/**
	 * @return
	 */
	public ItemDataSource getItemDataSource() {
		return ItemDataSource.getItemDataSourceById(itemDataSourceId);
	}

	// -----------------------------------------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getNavigationId() {
		return navigationId;
	}

	public void setNavigationId(long navigationId) {
		this.navigationId = navigationId;
	}

	public String getLv2CidsStr() {
		return lv2CidsStr;
	}

	public void setLv2CidsStr(String lv2CidsStr) {
		this.lv2CidsStr = lv2CidsStr;
	}

	public String getItemIdsStr() {
		return itemIdsStr;
	}

	public void setItemIdsStr(String itemIdsStr) {
		this.itemIdsStr = itemIdsStr;
	}

	public int getItemDataSourceId() {
		return itemDataSourceId;
	}

	public void setItemDataSourceId(int itemDataSourceId) {
		this.itemDataSourceId = itemDataSourceId;
	}

}
