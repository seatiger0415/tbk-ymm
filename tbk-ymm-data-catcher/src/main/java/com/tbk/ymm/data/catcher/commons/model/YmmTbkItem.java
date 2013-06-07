package com.tbk.ymm.data.catcher.commons.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.taobao.api.domain.TaobaokeItem;
import com.tbk.ymm.data.catcher.commons.enums.YmmTbkItemTable;

/**
 * 通过淘宝客接口获取的 淘宝客推广的单个商品，和TaobaokeItem对应
 * 
 * @author David (qidawei@xiaomi.com)
 */
public class YmmTbkItem {

	// 两个一模一样的表，当前查询使用一张表，更新时更新另一张表，更新完后再将查询切换到这个表
	// 当前使用哪个表需要在ymm_const表中查询
	public static final String TABLE_1 = YmmTbkItemTable.TABLE_1.getTableName();
	public static final String TABLE_2 = YmmTbkItemTable.TABLE_2.getTableName();

	private long id; // 自增id
	private long cid; // 这个cid只能自己填进去
	//
	private Date createTime;
	private Date updateTime;

	// 下面的字段是API返回的字段
	//
	private Long numIid; // 淘宝客商品数字id
	private String nick; // 卖家昵称
	private String title; // 商品title 宝贝名称
	private int price; // 商品价格 单位：分
	private String itemLocation; // 商品所在地
	private Long sellerCreditScore; // 卖家信用等级
	//
	private String clickUrl; // 推广点击url
	private String shopClickUrl; // 商品所在店铺的推广点击url
	//
	private String picUrl; // 图片url
	private int commissionRate; // 淘宝客佣金比率，比如：123456代表12.3456%
	private int commission; // 淘宝客佣金，单位：分
	// 这俩参数没理解
	private int commissionNum; // 累计成交量.注：返回的数据是30天内累计推广量
	private int commissionVolume; // 累计总支出佣金量；单位：分
	//
	private Long volume; // 30天内交易量
	//
	// 下面两个字段可能取不到。 字段先放到表里
	private String taobaokeCatClickUrl; // 商品所在类目的推广链接，这个字段好像不在请求的fields范围内，取不到
	private int promotionPrice; // 促销价格 单位：分

	public static final YmmTbkItem getInstanceFromTBItem(TaobaokeItem taobaokeItem) throws Exception {
		YmmTbkItem ymmItem = new YmmTbkItem();
		ymmItem.setNumIid(taobaokeItem.getNumIid());
		ymmItem.setNick(taobaokeItem.getNick());
		ymmItem.setTitle(taobaokeItem.getTitle());
		ymmItem.setPrice(priceStrToFen(taobaokeItem.getPrice()));
		ymmItem.setPromotionPrice(priceStrToFen(taobaokeItem.getPromotionPrice()));
		ymmItem.setItemLocation(taobaokeItem.getItemLocation());
		ymmItem.setSellerCreditScore(taobaokeItem.getSellerCreditScore());
		ymmItem.setClickUrl(taobaokeItem.getClickUrl());
		ymmItem.setShopClickUrl(taobaokeItem.getShopClickUrl());
		ymmItem.setTaobaokeCatClickUrl(taobaokeItem.getTaobaokeCatClickUrl());
		ymmItem.setPicUrl(taobaokeItem.getPicUrl());
		ymmItem.setCommissionRate(rateStrToInt(taobaokeItem.getCommissionRate()));
		ymmItem.setCommission(priceStrToFen(taobaokeItem.getCommission()));
		ymmItem.setCommissionNum(Integer.parseInt(taobaokeItem.getCommissionNum()));
		ymmItem.setCommissionVolume(priceStrToFen(taobaokeItem.getCommissionVolume()));
		ymmItem.setVolume(taobaokeItem.getVolume());
		//
		Calendar c = Calendar.getInstance(Locale.CHINA);
		ymmItem.setCreateTime(c.getTime());
		ymmItem.setUpdateTime(c.getTime());
		//
		return ymmItem;
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

	/**
	 * str(1234.56) => int(123456);接口返回的数据如果是1234.56，就是12.3456%
	 * 
	 * @param rateStr
	 * @return
	 */
	private static int rateStrToInt(String rateStr) {
		return (int) (Double.parseDouble(rateStr) * 100);
	}

	public long getId() {
		return id;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getNumIid() {
		return numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(int promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public String getItemLocation() {
		return itemLocation;
	}

	public void setItemLocation(String itemLocation) {
		this.itemLocation = itemLocation;
	}

	public Long getSellerCreditScore() {
		return sellerCreditScore;
	}

	public void setSellerCreditScore(Long sellerCreditScore) {
		this.sellerCreditScore = sellerCreditScore;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public String getShopClickUrl() {
		return shopClickUrl;
	}

	public void setShopClickUrl(String shopClickUrl) {
		this.shopClickUrl = shopClickUrl;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public int getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(int commissionRate) {
		this.commissionRate = commissionRate;
	}

	public int getCommission() {
		return commission;
	}

	public void setCommission(int commission) {
		this.commission = commission;
	}

	public int getCommissionNum() {
		return commissionNum;
	}

	public void setCommissionNum(int commissionNum) {
		this.commissionNum = commissionNum;
	}

	public int getCommissionVolume() {
		return commissionVolume;
	}

	public void setCommissionVolume(int commissionVolume) {
		this.commissionVolume = commissionVolume;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
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

	public String getTaobaokeCatClickUrl() {
		return taobaokeCatClickUrl;
	}

	public void setTaobaokeCatClickUrl(String taobaokeCatClickUrl) {
		this.taobaokeCatClickUrl = taobaokeCatClickUrl;
	}

}
