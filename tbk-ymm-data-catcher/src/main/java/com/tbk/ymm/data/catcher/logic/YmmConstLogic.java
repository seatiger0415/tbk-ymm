package com.tbk.ymm.data.catcher.logic;

import java.util.Map;

import com.tbk.ymm.data.catcher.commons.enums.YmmConstEnum;
import com.tbk.ymm.data.catcher.commons.model.YmmConst;


/**
 * @author David (qidawei@xiaomi.com)
 */
public interface YmmConstLogic {
	
	/**
	 * @return
	 */
	public Map<YmmConstEnum, YmmConst> getAll();

}
