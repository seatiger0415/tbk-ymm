package com.tbk.ymm.data.catcher.commons.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * ymm_const表中的配置必须在这里注册才有用
 * 
 * @author David (qidawei@xiaomi.com)
 */
public enum YmmConstEnum {
	Ymm_fav_item(1, "标示ymm_favorite_item使用哪张表"), //
	Ymm_tbk_item(2, "标示ymm_tbk_item使用哪张表"), //
	;

	private static final Map<Integer, YmmConstEnum> map = Maps.newHashMap();
	static {
		for (YmmConstEnum mmConstEnum : YmmConstEnum.values()) {
			map.put(mmConstEnum.getId(), mmConstEnum);
		}
	}

	private int id;

	private YmmConstEnum(int id, String description) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static YmmConstEnum getEnumById(int id) {
		return map.get(id);
	}

}
