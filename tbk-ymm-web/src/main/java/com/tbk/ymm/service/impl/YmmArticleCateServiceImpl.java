package com.tbk.ymm.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tbk.ymm.commons.consts.YmmConsts;
import com.tbk.ymm.commons.model.article.YmmArticleCate;
import com.tbk.ymm.dao.article.YmmArticleCateDAO;
import com.tbk.ymm.service.YmmArticleCateService;

@Service
public class YmmArticleCateServiceImpl implements YmmArticleCateService {

	@Autowired
	private YmmArticleCateDAO ymmArticleCateDAO;

	@Override
	public List<YmmArticleCate> getArtileCateList(int curId) {
		// TODO cache
		Map<Integer, YmmArticleCate> allMap = ymmArticleCateDAO.getAllMap();
		YmmArticleCate curArticleCate = allMap.get(curId);
		if (null == curArticleCate) {
			curId = YmmConsts.SHOPPING_CATE_ID;
			curArticleCate = allMap.get(curId);
			//
			if (null == curArticleCate) {
				return Collections.emptyList();
			}
		}
		//
		int topId = curId;
		if (YmmArticleCate.IS_P_NO == curArticleCate.getIsParent()) {
			topId = curArticleCate.getParentId();
		}
		//
		return ymmArticleCateDAO.getSubList(topId);
	}

	@Override
	public List<YmmArticleCate> getAllShoppingArticleCateList() {
		// TODO cache
		return ymmArticleCateDAO.getSubList(YmmConsts.SHOPPING_CATE_ID);
	}

	@Override
	public List<YmmArticleCate> getAllExperienceArticleCateList() {
		// TODO cache
		return ymmArticleCateDAO.getSubList(YmmConsts.EXPERIENCE_CATE_ID);
	}

}
