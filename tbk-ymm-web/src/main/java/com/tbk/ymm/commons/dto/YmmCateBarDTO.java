package com.tbk.ymm.commons.dto;

import java.util.List;

import com.tbk.ymm.commons.enums.CateType;
import com.tbk.ymm.commons.model.YmmNavigationCate;
import com.tbk.ymm.data.catcher.commons.model.YmmItemCate;

/**
 * 具体分类页面的导航工具栏的所有分类，整合成一个模型；<br/>
 * 因为网站的分类展现出来会和淘宝的不一致：导航分类可能是淘宝一级分类的集合
 * 
 * @author David (qidawei@xiaomi.com)
 */
public class YmmCateBarDTO {

	private long inputCateId;
	private CateType inputCateType;

	// 网站导航栏
	private List<YmmNavigationCate> navigationList;
	//
	// 当前导航栏下的一级分类列表
	private List<YmmItemCate> lv1CateList;
	// 当前导航栏下的二级分类列表
	private List<YmmItemCate> lv2CateList;

	/**
	 * 简易打印，列表只打印大小
	 * 
	 * @return
	 */
	public String toStringSimple() {
		StringBuilder sb = new StringBuilder();
		sb.append("{inputCateId: ").append(inputCateId).append("; inputCateType: ").append(inputCateType)
				.append("; navigationList size: ").append(navigationList.size()).append("; lv1CateList size: ")
				.append(lv1CateList.size()).append("; lv2CateList size: ").append(lv2CateList.size()).append("}");
		return sb.toString();
	}

	/*
	 * @Override
	 * public String toString() {
	 * StringBuilder sb = new StringBuilder();
	 * sb.append("{inputCateId: ").append(inputCateId).append("; inputCateType: ").append(inputCateType)
	 * .append("; navigationList: ").append(navigationList.size()).append("; lv1CateList: ")
	 * .append(lv1CateList.size()).append("; lv2CateList: ").append(lv2CateList.size()).append("}");
	 * return sb.toString();
	 * }
	 */

	public List<YmmNavigationCate> getNavigationList() {
		return navigationList;
	}

	public void setNavigationList(List<YmmNavigationCate> navigationList) {
		this.navigationList = navigationList;
	}

	public List<YmmItemCate> getLv1CateList() {
		return lv1CateList;
	}

	public void setLv1CateList(List<YmmItemCate> lv1CateList) {
		this.lv1CateList = lv1CateList;
	}

	public List<YmmItemCate> getLv2CateList() {
		return lv2CateList;
	}

	public void setLv2CateList(List<YmmItemCate> lv2CateList) {
		this.lv2CateList = lv2CateList;
	}

	public long getInputCateId() {
		return inputCateId;
	}

	public void setInputCateId(long inputCateId) {
		this.inputCateId = inputCateId;
	}

	public CateType getInputCateType() {
		return inputCateType;
	}

	public void setInputCateType(CateType inputCateType) {
		this.inputCateType = inputCateType;
	}

}
