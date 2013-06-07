package com.tbk.ymm.data.catcher.commons.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.taobao.api.domain.FavoriteItem;
import com.tbk.ymm.data.catcher.commons.enums.YmmFavoriteItemTable;
import com.tbk.ymm.data.catcher.utils.TrackIidToNumIidUtil;

/**
 * 对应taobao.categoryrecommend.items.get 返回的 FavoriteItem
 * 
 * @author David (qidawei@xiaomi.com)
 */
public class YmmFavoriteItem {

	// 两个一模一样的表，当前查询使用一张表，更新时更新另一张表，更新完后再将查询切换到这个表
	// 当前使用哪个表需要在ymm_const表中查询
	public static final String TABLE_1 = YmmFavoriteItemTable.TABLE_1.getTableName();
	public static final String TABLE_2 = YmmFavoriteItemTable.TABLE_2.getTableName();

	// 以下字段是网站内加上的
	private long id;
	private long cid;
	private Long numIid; // 商品唯一id，这个字段来自trackIid，这种自己解析trackIid的方式平台不推荐。但是，这个字段需要拿来去重，是必须的
	private Date createTime;
	private Date updateTime;
	//
	// 以下字段来自FavoriteItem
	private String trackIid; // 商品id（具有跟踪效果）代替原来的item_id 详细说明
	private String itemName; // 商品名称
	private String itemPicture; // 商品图片地址
	private String itemUrl; // 商品的详情页面地址
	private int itemPrice; // 商品价格
	private int promotionPrice; // 促销价格
	private int sellCount; // 商品销售次数

	//
	// 以下字段是冗余字段

	/**
	 * @param favoriteItem
	 * @return
	 */
	public static YmmFavoriteItem getInstanceFromFavItem(FavoriteItem favoriteItem) {
		if (null == favoriteItem) {
			return null;
		}
		if (null == favoriteItem.getTrackIid()) {
			return null;
		}
		YmmFavoriteItem ymmFavoriteItem = new YmmFavoriteItem();
		ymmFavoriteItem.setTrackIid(favoriteItem.getTrackIid());
		try {
			ymmFavoriteItem.setNumIid(TrackIidToNumIidUtil.parseNumIidFromTrackIid(favoriteItem.getTrackIid()));
		} catch (Exception e) {
			e.printStackTrace();
			ymmFavoriteItem.setNumIid(null);
		}
		ymmFavoriteItem.setItemName(favoriteItem.getItemName());
		ymmFavoriteItem.setItemPicture(favoriteItem.getItemPictrue());
		ymmFavoriteItem.setItemUrl(favoriteItem.getItemUrl());
		ymmFavoriteItem.setItemPrice(priceStrToFen(favoriteItem.getItemPrice()));
		ymmFavoriteItem.setPromotionPrice(priceStrToFen(favoriteItem.getPromotionPrice()));
		ymmFavoriteItem.setSellCount((int) (null == favoriteItem.getSellCount() ? 0 :
				favoriteItem.getSellCount()));
		//
		Calendar c = Calendar.getInstance(Locale.CHINA);
		ymmFavoriteItem.setCreateTime(c.getTime());
		ymmFavoriteItem.setUpdateTime(c.getTime());
		//
		return ymmFavoriteItem;
	}

	/**
	 * str(12.34) => int(1234)
	 * 
	 * @param priceStr
	 * @return
	 */
	private static int priceStrToFen(String priceStr) {
		return (int) (Double.parseDouble(priceStr) * 100);
	}

	// ----------------------------------------

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public String getItemName()
	{
		return itemName;
	}

	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	public String getItemPicture() {
		return itemPicture;
	}

	public Long getNumIid() {
		return numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}

	public void setItemPicture(String itemPicture) {
		this.itemPicture = itemPicture;
	}

	public String getItemUrl()
	{
		return itemUrl;
	}

	public void setItemUrl(String itemUrl)
	{
		this.itemUrl = itemUrl;
	}

	public String getTrackIid()
	{
		return trackIid;
	}

	public void setTrackIid(String trackIid)
	{
		this.trackIid = trackIid;
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

}
