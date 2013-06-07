package com.tbk.ymm.data.catcher.commons.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.taobao.api.domain.Feature;
import com.taobao.api.domain.ItemCat;

/**
 * 商品分类，直接从淘宝抓取；来自淘宝sdk的ItemCat类
 * 
 * @author David (qidawei@xiaomi.com)
 */
public class YmmItemCate {

	public static final String TABLE = "ymm_item_cate";
	public static final int IS_P_YES = 1;
	public static final int IS_P_NO = -1;
	//
	public static final int STATUS_UNKNOWN = 0;
	public static final int STATUS_NORMAL = 1;
	public static final int STATUS_DELETE = -1;

	private Long cid;
	private String name;
	private Long parentCid;
	private Integer isParent = IS_P_NO; // 是否还有子类目 1: yes; -1:no
	private Long sortOrder; // 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
	private Integer status = STATUS_NORMAL; // ItemCat.status:状态。可选值:normal(正常),deleted(删除)
	//
	private Date createTime;
	private Date updateTime;
	//
	// 作为冗余字段
	private List<Feature> features;

	// 冗余字段
	private boolean selected; // 是否被选中

	/**
	 * 根据淘宝API返回的ItemCat对象创建一个TmmItemCate实例
	 * 
	 * @param itemCat
	 * @return
	 */
	public static final YmmItemCate getInstanceFromItemCat(ItemCat itemCat) {
		YmmItemCate ymmItemCate = new YmmItemCate();
		ymmItemCate.setCid(itemCat.getCid());
		if (null == itemCat.getIsParent() || !itemCat.getIsParent()) {
			ymmItemCate.setIsParent(IS_P_NO);
		} else {
			ymmItemCate.setIsParent(IS_P_YES);
		}
		ymmItemCate.setName(itemCat.getName());
		ymmItemCate.setParentCid(itemCat.getParentCid());
		ymmItemCate.setSortOrder(itemCat.getSortOrder());
		String status = itemCat.getStatus();
		if ("normal".equals(status)) {
			ymmItemCate.setStatus(STATUS_NORMAL);
		} else if ("deleted".equals(status)) {
			ymmItemCate.setStatus(STATUS_DELETE);
		} else {
			ymmItemCate.setStatus(STATUS_UNKNOWN);
		}
		Calendar c = Calendar.getInstance(Locale.CHINA);
		ymmItemCate.setCreateTime(c.getTime());
		ymmItemCate.setUpdateTime(c.getTime());
		//
		return ymmItemCate;
	}

	// -------------------------------------

	public boolean selected() {
		return selected;
	}

	public void buildSelected(boolean selected) {
		this.selected = selected;
	}

	public List<Feature> features() {
		return features;
	}

	public void buildFeatures(List<Feature> features) {
		this.features = features;
	}

	public Long getCid()
	{
		return cid;
	}

	public void setCid(Long cid)
	{
		this.cid = cid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Long getParentCid()
	{
		return parentCid;
	}

	public void setParentCid(Long parentCid)
	{
		this.parentCid = parentCid;
	}

	public Long getSortOrder()
	{
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder)
	{
		this.sortOrder = sortOrder;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
