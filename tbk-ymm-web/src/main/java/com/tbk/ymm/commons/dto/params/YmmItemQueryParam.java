package com.tbk.ymm.commons.dto.params;

import java.util.List;

import com.tbk.ymm.commons.enums.ItemDataSource;
import com.tbk.ymm.commons.enums.ItemOrderType;

/**
 * 查询商品时使用的参数
 * 
 * @author David (qidawei@xiaomi.com)
 */
public class YmmItemQueryParam {

	// 基础条件
	private long cid = 0;
	private List<Long> cidList = null;
	private int smallPrice;
	private int bigPrice;
	// 分页有关
	private int curPage = 0;
	private int pageSize = 0;
	// 排序
	private ItemOrderType orderType = null;
	// 数据源
	private ItemDataSource itemDataSource;
	//
	// 以下备用
	private String keyword = null; // 关键字
	private int tagId = 0; // 将来需要给商品自定义标签（最好能自动），比如给一个防辐射服打上牌子的标签什么的

	// TODO

	public long getCid() {
		return cid;
	}

	public List<Long> getCidList() {
		return cidList;
	}

	public void setCidList(List<Long> cidList) {
		this.cidList = cidList;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public int getSmallPrice() {
		return smallPrice;
	}

	public void setSmallPrice(int smallPrice) {
		this.smallPrice = smallPrice;
	}

	public int getBigPrice() {
		return bigPrice;
	}

	public void setBigPrice(int bigPrice) {
		this.bigPrice = bigPrice;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public ItemOrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(ItemOrderType orderType) {
		this.orderType = orderType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public ItemDataSource getItemDataSource() {
		return itemDataSource;
	}

	public void setItemDataSource(ItemDataSource itemDataSource) {
		this.itemDataSource = itemDataSource;
	}

}
