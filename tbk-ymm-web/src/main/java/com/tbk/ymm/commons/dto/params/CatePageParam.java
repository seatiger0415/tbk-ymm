package com.tbk.ymm.commons.dto.params;

/**
 * 二级分类页从页面传过来的参数列表
 * 
 * @author David (qidawei@xiaomi.com)
 */
public class CatePageParam {

	private int cateId;
	private int curPage;
	private int smallPrice;
	private int bigPrice;

	public int getCateId() {
		return cateId;
	}

	public void setCateId(int cateId) {
		this.cateId = cateId;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
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

}
