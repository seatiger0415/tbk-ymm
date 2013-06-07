package com.tbk.ymm.logic.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tbk.ymm.dao.YmmFavoriteItemDAO;
import com.tbk.ymm.data.catcher.commons.enums.YmmConstEnum;
import com.tbk.ymm.data.catcher.commons.enums.YmmFavoriteItemTable;
import com.tbk.ymm.data.catcher.commons.model.YmmConst;
import com.tbk.ymm.data.catcher.commons.model.YmmFavoriteItem;
import com.tbk.ymm.logic.YmmConstLogic;
import com.tbk.ymm.logic.YmmFavoriteItemLogic;

/**
 * @author David (qidawei@xiaomi.com)
 */
@Service
public class YmmFavoriteItemLogicImpl implements YmmFavoriteItemLogic {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private YmmConstLogic ymmConstLogic;

	@Autowired
	private YmmFavoriteItemDAO ymmFavoriteItemDAO;

	@Override
	public List<YmmFavoriteItem> getByCidListInSellCountOrder(List<Long> cidList, int offset, int num) {
		if (null == cidList || cidList.isEmpty()) {
			return Collections.emptyList();
		}
		YmmFavoriteItemTable itemTable = getItemTable();
		if (null == itemTable) {
			logger.error("YmmFavoriteItemLogicImpl.getByCidListInSellCountOrder.can not get enum YmmFavoriteItemTable");
			return Collections.emptyList();
		}
		// TODO cache
		return ymmFavoriteItemDAO.getByCidListInSellCountOrder(itemTable, cidList, offset, num);

	}

	@Override
	public List<YmmFavoriteItem> getByTrackIidList(List<String> trackIidList) {
		if (null == trackIidList || trackIidList.isEmpty()) {
			return Collections.emptyList();
		}
		YmmFavoriteItemTable itemTable = getItemTable();
		if (null == itemTable) {
			logger.error("YmmFavoriteItemLogicImpl.getByTrackIidList.can not get enum YmmFavoriteItemTable");
			return Collections.emptyList();
		}
		// TODO cache
		return ymmFavoriteItemDAO.getByTrackIidList(itemTable, trackIidList);
	}

	@Override
	public int getCountByCidList(List<Long> cidList) {
		if (null == cidList || cidList.isEmpty()) {
			return 0;
		}
		YmmFavoriteItemTable itemTable = getItemTable();
		if (null == itemTable) {
			logger.error("YmmFavoriteItemLogicImpl.getCountByCidList.can not get enum YmmFavoriteItemTable");
			return 0;
		}
		return ymmFavoriteItemDAO.getCountByCidList(itemTable, cidList);
	}

	// -------------------------------------

	/**
	 * @return
	 */
	private YmmFavoriteItemTable getItemTable() {
		Map<YmmConstEnum, YmmConst> constMap = ymmConstLogic.getAll();
		YmmConst favTableConst = constMap.get(YmmConstEnum.Ymm_fav_item);
		if (null == favTableConst) {
			logger.error("YmmFavoriteItemLogicImpl.getItemTable.favTableConst is null");
			return null;
		}
		return YmmFavoriteItemTable.getEnumByTableName(favTableConst.getConstValue());
	}
}
