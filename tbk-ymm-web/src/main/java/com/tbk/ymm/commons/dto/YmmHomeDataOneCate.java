package com.tbk.ymm.commons.dto;

import java.util.Collections;
import java.util.List;

import com.tbk.ymm.commons.model.YmmNavigationCate;
import com.tbk.ymm.commons.model.article.YmmArticle;
import com.tbk.ymm.data.catcher.commons.model.YmmItemCate;

/**
 * 封装主页主题部分展示的数据 => 一个类别的数据
 * 
 * @author David (qidawei@xiaomi.com)
 */
public class YmmHomeDataOneCate {

	public static final int LV2_CATE_NUM = 4;
	public static final int RECOMMEND_ITEM_NUM = 7;
	private static final int HOME_ARTICLE_NUM = 5;

	private YmmNavigationCate navigationCate; // 当前导航类别
	private List<YmmItemCate> itemCateList; // 当前导航类别的子类别列表 最多LV2_CATE_NUM个
	private List<YmmItem> itemList; // 首页这个类别的推荐商品 最多RECOMMEND_ITEM_NUM个
	//
	private int articleCateId; // 本类目相关的文章类目
	private List<YmmArticle> articleList; // 这个类别相关的推荐文章

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{navigationCate:").append(navigationCate).append("; itemCateList:").append(itemCateList)
				.append("; itemList:").append(itemList).append("; articleList").append(articleList).append("}");
		return sb.toString();
	}

	public YmmNavigationCate getNavigationCate() {
		return navigationCate;
	}

	public void setNavigationCate(YmmNavigationCate navigationCate) {
		this.navigationCate = navigationCate;
	}

	public List<YmmItemCate> getItemCateList() {
		if (null == itemCateList) {
			return Collections.emptyList();
		}
		if (itemCateList.size() >= LV2_CATE_NUM) {
			return itemCateList.subList(0, LV2_CATE_NUM);
		}
		return itemCateList;
	}

	public void setItemCateList(List<YmmItemCate> itemCateList) {
		this.itemCateList = itemCateList;
	}

	public List<YmmItem> getItemList() {
		if (null == itemList) {
			return Collections.emptyList();
		}
		if (itemList.size() >= RECOMMEND_ITEM_NUM) {
			return itemList.subList(0, RECOMMEND_ITEM_NUM);
		}
		return itemList;
	}

	public void setItemList(List<YmmItem> itemList) {
		this.itemList = itemList;
	}

	public List<YmmArticle> getArticleList() {
		if (null == articleList) {
			return Collections.emptyList();
		}
		if (articleList.size() >= HOME_ARTICLE_NUM) {
			return articleList.subList(0, HOME_ARTICLE_NUM);
		}
		return articleList;
	}

	public void setArticleList(List<YmmArticle> articleList) {
		this.articleList = articleList;
	}

	public int getArticleCateId() {
		return articleCateId;
	}

	public void setArticleCateId(int articleCateId) {
		this.articleCateId = articleCateId;
	}

}
