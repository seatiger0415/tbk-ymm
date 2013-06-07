package com.tbk.ymm.commons.model;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;
import com.tbk.ymm.data.catcher.commons.model.YmmItemCate;

/**
 * 孕妈妈导航栏上的特殊分类；因为网站导航不能直接展示淘宝原生的分类，所以在淘宝分类YmmItemCate的基础上做这个扩展；<br/>
 * “尽量”使用淘宝原生的数据，所以只定义导航栏分类，而不完全重定义孕妈妈站内的分类，即二级分类依然使用淘宝的分类。
 * 
 * @author David (qidawei@xiaomi.com)
 */
public class YmmNavigationCate {

	public static final String TABLE = "ymm_navigation_cate";

	public static final int STATUS_COMMON = 1; // 普通
	public static final int STATUS_HOT = 2; // 热门
	public static final int STATUS_HIDE = -1; // 隐藏

	private long id;
	private String name;
	private String itemCateIds; // 本导航类包含的YmmItemCate ids，用逗号隔开
	private int status = STATUS_COMMON;

	// 冗余数据
	private boolean selected = false;
	private List<YmmItemCate> lv2CateList;// 本导航下的 二级分类列表

	/**
	 * @return
	 */
	public List<Long> getLv1ItemCateIdlist() {
		if (StringUtils.isEmpty(itemCateIds)) {
			return Collections.emptyList();
		}
		String[] cidArr = itemCateIds.split(",");
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
	 * 此方法依赖lv2CateList这个冗余属性，只是单纯的解析出其中cid
	 * 
	 * @return
	 */
	public List<Long> getLv2ItemCateIdlist() {
		if (null == lv2CateList || lv2CateList.isEmpty()) {
			return Collections.emptyList();
		}
		List<Long> cidList = Lists.newArrayListWithExpectedSize(lv2CateList.size());
		for (YmmItemCate ymmItemCate : lv2CateList) {
			cidList.add(ymmItemCate.getCid());
		}
		return cidList;
	}

	public boolean selected() {
		return selected;
	}

	public void buildSelected(boolean selected) {
		this.selected = selected;
	}

	public List<YmmItemCate> lv2CateList() {
		return lv2CateList;
	}

	public void buildLv2CateList(List<YmmItemCate> lv2CateList) {
		this.lv2CateList = lv2CateList;
	}

	// -----------------------------------------

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemCateIds() {
		return itemCateIds;
	}

	public void setItemCateIds(String itemCateIds) {
		this.itemCateIds = itemCateIds;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
