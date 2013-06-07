package com.tbk.ymm.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.tbk.ymm.commons.dto.YmmItem;
import com.tbk.ymm.commons.enums.ItemDataSource;
import com.tbk.ymm.commons.model.YmmNavigationCate;
import com.tbk.ymm.commons.model.YmmRecommendEachNavigation;
import com.tbk.ymm.dao.YmmRecommendEachNavigationDAO;
import com.tbk.ymm.dao.cate.YmmItemCateDAO;
import com.tbk.ymm.logic.YmmFavoriteItemLogic;
import com.tbk.ymm.logic.YmmItemLogic;
import com.tbk.ymm.logic.YmmRecommendLogic;

/**
 * @author David (qidawei@xiaomi.com)
 */
@Service
public class YmmRecommendLogicImpl implements YmmRecommendLogic {

	@Autowired
	private YmmFavoriteItemLogic ymmFavoriteItemLogic;
	@Autowired
	private YmmRecommendEachNavigationDAO recommedByNavDAO;
	@Autowired
	private YmmItemCateDAO ymmItemCateDAO;
	@Autowired
	private YmmItemLogic ymmItemLogic;

	@Override
	public List<YmmItem> getRecommendByNav(YmmNavigationCate navCate, int num) {
		List<YmmRecommendEachNavigation> recommendConfList = recommedByNavDAO.getByNavigationId(navCate.getId());
		//
		if (null == recommendConfList || recommendConfList.isEmpty()) {
			// 没有配置
			return getRecommendListByNavDefault(navCate, num);
		} else {
			return getRecommendListByConf(recommendConfList, num);
		}
	}

	// ----------------------------------------

	/**
	 * 根据ymm_recommend_each_navigation表的配置来获取推荐信息
	 * 
	 * @param recommendConfList
	 * @return
	 */
	private List<YmmItem> getRecommendListByConf(List<YmmRecommendEachNavigation> recommendConfList, int num) {
		List<YmmItem> list = Lists.newArrayList();
		//
		for (YmmRecommendEachNavigation recommendEachNavigation : recommendConfList) {
			// 若配置了推荐商品，则优先取商品；否则取配置的二级类目下的排序最好高的商品
			List<String> itemIdlist = recommendEachNavigation.getItemIdList();
			ItemDataSource itemDataSource = recommendEachNavigation.getItemDataSource();
			if (!itemIdlist.isEmpty() && null != itemDataSource) {
				List<YmmItem> itemLlist = ymmItemLogic.getItemList(itemDataSource, itemIdlist);
				list.addAll(itemLlist);
			} else {
				// TODO cache，这个地方效率不高，需要多次查询数据库
				List<Long> lv2CidList = recommendEachNavigation.getLv2CidList();
				for (Long lv2Cid : lv2CidList) {
					List<YmmItem> itemLlist = ymmItemLogic.getByCidListInSellCountOrder(Lists.newArrayList(lv2Cid), 0,
							YmmRecommendEachNavigation.NUM_EACH_LV2CATE);
					list.addAll(itemLlist);
				}
			}
		}
		//
		return list;
	}

	/**
	 * 默认方式取一个导航类目的推荐商品，方法很简单：此导航类目下所有二级类目的商品中，按某个顺序（目前是按销量）排序； <br/>
	 * 这样的结果不是很好，比如防辐射服下面全是防辐射面料的数据
	 * 
	 * @param navCate
	 * @param num
	 * @return
	 */
	private List<YmmItem> getRecommendListByNavDefault(YmmNavigationCate navCate, int num) {
		List<Long> lv2CateIdList = navCate.getLv2ItemCateIdlist();
		return ymmItemLogic.getByCidListInSellCountOrder(lv2CateIdList, 0, num);
	}

}
