package com.tbk.ymm.commons.dto;

import org.apache.commons.lang.StringUtils;

import com.tbk.ymm.commons.consts.YmmConsts;
import com.tbk.ymm.data.catcher.commons.model.YmmFavoriteItem;
import com.tbk.ymm.data.catcher.commons.model.YmmItemCate;
import com.tbk.ymm.data.catcher.commons.model.YmmTbkItem;

/**
 * 网站的商品模型；底层数据源可能有多个，比如YmmTbkItem, YmmFavoriteItem等，这个模型对其统一封装
 * 
 * @author David (qidawei@xiaomi.com)
 */
public class YmmItem {

	// 基本数据
	// numIid 和 trackIid选其一
	private Long numIid; // 淘宝客商品数字id
	private String trackIid; // 商品id（具有跟踪效果）代替原来的item_id 详细说明
	//
	private long cid;
	private String itemName; // 商品名称
	private String itemPicture; // 商品图片地址
	private String itemUrl; // 商品的详情页面地址
	private int itemPrice; // 商品价格
	private int promotionPrice; // 促销价格
	private int sellCount; // 商品销售次数
	//
	private YmmItemCate itemCate; // 此商品的分类信息
	//
	// 先把可能得数据源冗余进来
	private YmmTbkItem ymmTbkItem;
	private YmmFavoriteItem ymmFavoriteItem;

	public static final YmmItem getFromYmmFavoriteItem(YmmFavoriteItem ymmFavoriteItem) {
		YmmItem ymmItem = new YmmItem();
		ymmItem.setYmmFavoriteItem(ymmFavoriteItem);
		//
		ymmItem.setTrackIid(ymmFavoriteItem.getTrackIid());
		ymmItem.setNumIid(0L);
		ymmItem.setCid(ymmFavoriteItem.getCid());
		ymmItem.setItemName(ymmFavoriteItem.getItemName());
		ymmItem.setItemPicture(ymmFavoriteItem.getItemPicture());
		ymmItem.setItemUrl(ymmFavoriteItem.getItemUrl());
		ymmItem.setItemPrice(ymmFavoriteItem.getItemPrice());
		ymmItem.setPromotionPrice(ymmFavoriteItem.getPromotionPrice());
		ymmItem.setSellCount(ymmFavoriteItem.getSellCount());
		return ymmItem;
	}

	public YmmItemCate itemCate() {
		return itemCate;
	}

	public String itemNameWithCut() {
		if (StringUtils.isEmpty(itemName)) {
			return "";
		}
		if (itemName.length() > YmmConsts.HOME_ITEM_NAME_LEN) {
			return itemName.substring(0, YmmConsts.HOME_ITEM_NAME_LEN);
		}
		return itemName;
	}

	public void buildItemCate(YmmItemCate itemCate) {
		this.itemCate = itemCate;
	}

	// ---------------------------------

	public Long getNumIid() {
		return numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}

	public String getTrackIid() {
		return trackIid;
	}

	public void setTrackIid(String trackIid) {
		this.trackIid = trackIid;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemPicture() {
		return itemPicture;
	}

	public void setItemPicture(String itemPicture) {
		this.itemPicture = itemPicture;
	}

	public String getItemUrl() {
		return itemUrl;
	}

	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(int promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public int getSellCount() {
		return sellCount;
	}

	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}

	public YmmTbkItem getYmmTbkItem() {
		return ymmTbkItem;
	}

	public void setYmmTbkItem(YmmTbkItem ymmTbkItem) {
		this.ymmTbkItem = ymmTbkItem;
	}

	public YmmFavoriteItem getYmmFavoriteItem() {
		return ymmFavoriteItem;
	}

	public void setYmmFavoriteItem(YmmFavoriteItem ymmFavoriteItem) {
		this.ymmFavoriteItem = ymmFavoriteItem;
	}

}
