package com.tbk.ymm.data.catcher.logic.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.tbk.ymm.data.catcher.commons.enums.YmmConstEnum;
import com.tbk.ymm.data.catcher.commons.model.YmmConst;
import com.tbk.ymm.data.catcher.dao.ymm.YmmConstDAO;
import com.tbk.ymm.data.catcher.logic.YmmConstLogic;

/**
 * @author David (qidawei@xiaomi.com)
 */
@Service
public class YmmConstLogicImpl implements YmmConstLogic {

	@Autowired
	private YmmConstDAO ymmConstDAO;

	@Override
	public Map<YmmConstEnum, YmmConst> getAll() {
		Map<Integer, YmmConst> map = ymmConstDAO.getAll();
		Map<YmmConstEnum, YmmConst> retMap = Maps.newHashMapWithExpectedSize(map.size());
		for (Entry<Integer, YmmConst> entry : map.entrySet()) {
			YmmConstEnum constEnum = YmmConstEnum.getEnumById(entry.getKey());
			if (null != constEnum) {
				retMap.put(constEnum, entry.getValue());
			}
		}
		return retMap;
	}
}
