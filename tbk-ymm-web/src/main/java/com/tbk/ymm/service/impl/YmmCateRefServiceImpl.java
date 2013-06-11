package com.tbk.ymm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tbk.ymm.commons.enums.CateRefType;
import com.tbk.ymm.commons.model.YmmCateRef;
import com.tbk.ymm.dao.YmmCateRefDAO;
import com.tbk.ymm.service.YmmCateRefService;
import com.tbk.ymm.utils.collections.CollectionUtils;

@Service
public class YmmCateRefServiceImpl implements YmmCateRefService {

	@Autowired
	private YmmCateRefDAO ymmCateRefDAO;

	@Override
	public int getArticleCateIdByCateId(long cateId) {
		List<YmmCateRef> refList = ymmCateRefDAO.getListByCateIdAndType(cateId,
				CateRefType.articleCateId);
		if (CollectionUtils.isCollectionEmpty(refList)) {
			return 0;
		}
		//
		YmmCateRef ref = refList.get(0);
		if (null != ref) {
			return ref.getArticleCateId();
		}
		return 0;
	}

}
