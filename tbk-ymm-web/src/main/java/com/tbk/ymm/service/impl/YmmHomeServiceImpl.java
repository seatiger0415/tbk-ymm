package com.tbk.ymm.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.tbk.ymm.commons.dto.YmmCateBarDTO;
import com.tbk.ymm.commons.dto.YmmHomeDataOneCate;
import com.tbk.ymm.commons.dto.YmmItem;
import com.tbk.ymm.commons.enums.CateRefType;
import com.tbk.ymm.commons.model.YmmCateRef;
import com.tbk.ymm.commons.model.YmmNavigationCate;
import com.tbk.ymm.commons.model.YmmRecommendEachNavigation;
import com.tbk.ymm.commons.model.article.YmmArticle;
import com.tbk.ymm.dao.YmmCateRefDAO;
import com.tbk.ymm.dao.YmmRecommendEachNavigationDAO;
import com.tbk.ymm.dao.article.YmmArticleCateDAO;
import com.tbk.ymm.dao.cate.YmmItemCateDAO;
import com.tbk.ymm.data.catcher.commons.model.YmmItemCate;
import com.tbk.ymm.logic.YmmRecommendLogic;
import com.tbk.ymm.service.YmmArticleService;
import com.tbk.ymm.service.YmmCateService;
import com.tbk.ymm.service.YmmHomeService;
import com.tbk.ymm.utils.collections.CollectionUtils;

@Service
public class YmmHomeServiceImpl implements YmmHomeService {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private YmmCateService ymmCateService;
	@Autowired
	private YmmRecommendLogic ymmRecommendLogic;
	@Autowired
	private YmmArticleCateDAO ymmArticleCateDAO;
	@Autowired
	private YmmArticleService ymmArticleService;
	@Autowired
	private YmmCateRefDAO ymmCateRefDAO;
	@Autowired
	private YmmRecommendEachNavigationDAO recommedByNavDAO;
	@Autowired
	private YmmItemCateDAO ymmItemCateDAO;

	@Override
	public List<YmmHomeDataOneCate> getHomeData(List<YmmNavigationCate> navigationList) {
		List<YmmHomeDataOneCate> homeDataList = initAndSetCateInfo(navigationList);
		for (YmmHomeDataOneCate homeDataOneCate : homeDataList) {
			YmmNavigationCate navCate = homeDataOneCate.getNavigationCate();
			//
			List<YmmItem> itemList = ymmRecommendLogic.getRecommendByNav(navCate,
					YmmHomeDataOneCate.RECOMMEND_ITEM_NUM);
			homeDataOneCate.setItemList(itemList);
		}
		//
		buildArticleInfo(homeDataList);
		//
		logger.debug("YmmHomeServiceImpl.getHomeData:" + homeDataList);
		//
		return homeDataList;
	}

	// -------------------------------------------

	/**
	 * 把每个类目的推荐文章build进首页数据
	 * 
	 * @param homeDataList
	 */
	private void buildArticleInfo(List<YmmHomeDataOneCate> homeDataList) {
		//
		Map<Long, YmmCateRef> mapByCate = ymmCateRefDAO.getAllMapByCateIdAndType(CateRefType.articleCateId);
		for (YmmHomeDataOneCate ymmHomeDataOneCate : homeDataList) {
			YmmNavigationCate navCate = ymmHomeDataOneCate.getNavigationCate();
			if (null == navCate) {
				continue;
			}
			YmmCateRef ymmCateRef = mapByCate.get(navCate.getId());
			if (null == ymmCateRef) {
				continue;
			}
			//
			int articleCateId = ymmCateRef.getArticleCateId();
			//
			ymmHomeDataOneCate.setArticleCateId(articleCateId);
			List<YmmArticle> articleList = ymmArticleService.getListByArticleCateId(articleCateId, 5);
			ymmHomeDataOneCate.setArticleList(articleList);
		}
	}

	/**
	 * 初始化各个类别dto，并放入分类信息；TODO 这个查询比较多，可能需要优化
	 * 
	 * @param homeDataList
	 * @param navigationList
	 */
	private List<YmmHomeDataOneCate> initAndSetCateInfo(List<YmmNavigationCate> navigationList) {
		List<YmmHomeDataOneCate> homeDataList = Lists.newArrayListWithCapacity(navigationList.size());
		for (YmmNavigationCate navigationCate : navigationList) {
			YmmCateBarDTO ymmCateBarDTO = ymmCateService.getCateBar(navigationCate.getId());
			YmmHomeDataOneCate homeDataOneCate = new YmmHomeDataOneCate();
			//
			// navigationCate.buildLv2CateList(ymmCateBarDTO.getLv2CateList());
			//
			homeDataOneCate.setNavigationCate(navigationCate);
			homeDataOneCate.setItemCateList(getRecommendLv2CateListOfOneNavCate(ymmCateBarDTO));
			//
			homeDataList.add(homeDataOneCate);
		}
		return homeDataList;
	}

	/**
	 * 一个导航类目下推荐的二级类目；首先从ymm_recommend_each_navigation表中取，没有的话，取默认前N名
	 * 
	 * @param ymmCateBarDTO
	 * @return
	 */
	private List<YmmItemCate> getRecommendLv2CateListOfOneNavCate(YmmCateBarDTO ymmCateBarDTO) {
		YmmNavigationCate navCate = ymmCateBarDTO.getCurNavCate();
		if (null != navCate) {
			List<YmmRecommendEachNavigation> recommendConfList = recommedByNavDAO.getByNavigationId(navCate.getId());
			if (!CollectionUtils.isCollectionEmpty(recommendConfList)) {
				for (YmmRecommendEachNavigation recommend : recommendConfList) {
					List<Long> lv2CidList = recommend.getLv2CidList();
					if (!lv2CidList.isEmpty()) {
						return ymmItemCateDAO.getByCids(lv2CidList);
					}
				}
			}
		}
		return ymmCateBarDTO.getLv2CateList();
	}
}
