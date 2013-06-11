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
	public List<YmmFavoriteItem> getByCidListAndPrice(List<Long> cidList, int smallPrice,
			int bigPrice, int offset, int num) {
		if (null == cidList || cidList.isEmpty()) {
			return Collections.emptyList();
		}
		YmmFavoriteItemTable itemTable = getItemTable();
		if (null == itemTable) {
			logger.error("YmmFavoriteItemLogicImpl.getByCidListInSellCountOrder.can not get enum YmmFavoriteItemTable");
			return Collections.emptyList();
		}
		// TODO cache
		if ((smallPrice <= 0 && bigPrice <= 0) || (bigPrice > 0 && smallPrice > bigPrice)) {
			return ymmFavoriteItemDAO.getByCidList(itemTable, cidList, offset, num);
		} else {
			bigPrice = (0 == bigPrice) ? 100000 : bigPrice;
			return ymmFavoriteItemDAO.getByCidListAndPrice(itemTable, cidList,
					smallPrice * 100, bigPrice * 100, offset, num);
		}

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
	public int getCountByCidListAndPrice(List<Long> cidList, int smallPrice, int bigPrice) {
		if (null == cidList || cidList.isEmpty()) {
			return 0;
		}
		YmmFavoriteItemTable itemTable = getItemTable();
		if (null == itemTable) {
			logger.error("YmmFavoriteItemLogicImpl.getCountByCidList.can not get enum YmmFavoriteItemTable");
			return 0;
		}
		if ((smallPrice <= 0 && bigPrice <= 0) || (bigPrice > 0 && smallPrice > bigPrice)) {
			return ymmFavoriteItemDAO.getCountByCidList(itemTable, cidList);
		} else {
			bigPrice = (0 == bigPrice) ? 100000 : bigPrice;
			return ymmFavoriteItemDAO.getCountByCidListAndPrice(itemTable, cidList,
					smallPrice * 100, bigPrice * 100);
		}
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
