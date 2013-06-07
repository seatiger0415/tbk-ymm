package com.tbk.ymm.logic;

import java.util.List;

import com.tbk.ymm.commons.dto.YmmItem;
import com.tbk.ymm.commons.model.YmmNavigationCate;

/**
 * 推荐逻辑
 * 
 * @author David (qidawei@xiaomi.com)
 */
public interface YmmRecommendLogic {

	/**
	 * 根据导航类目id，获取推荐的商品
	 * 
	 * @param navCate
	 * @param num
	 * @return
	 */
	public List<YmmItem> getRecommendByNav(YmmNavigationCate navCate, int num);

}
