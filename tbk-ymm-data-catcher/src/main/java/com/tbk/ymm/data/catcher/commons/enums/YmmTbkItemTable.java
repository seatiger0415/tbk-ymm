package com.tbk.ymm.data.catcher.commons.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 定义tbk item的两个表名，防止传参的时候写错
 * 
 * @author David (qidawei@xiaomi.com)
 */
public enum YmmTbkItemTable {

	TABLE_1("ymm_tbk_item_1"), //
	TABLE_2("ymm_tbk_item_2"), //
	;

	private static Map<String, YmmTbkItemTable> hashMap = Maps.newHashMap();
	static {
		for (YmmTbkItemTable ymmItemTable : YmmTbkItemTable.values()) {
			hashMap.put(ymmItemTable.getTableName(), ymmItemTable);
		}
	}

	private YmmTbkItemTable(String tableName) {
		this.tableName = tableName;
	}

	private String tableName;

	public String getTableName() {
		return tableName;
	}

	public static YmmTbkItemTable getEnumByTableName(String tableName) {
		return hashMap.get(tableName);
	}

	/**
	 * 根据一个取另一个
	 * 
	 * @param ymmItemTable
	 * @return
	 */
	public static YmmTbkItemTable getTheOther(YmmTbkItemTable ymmItemTable) {
		if (TABLE_1.equals(ymmItemTable)) {
			return TABLE_2;
		} else {
			return TABLE_1;
		}
	}
}
