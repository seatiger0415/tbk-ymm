package com.tbk.ymm.commons.enums;

/**
 * CateRef的type字段的定义
 * 
 * @author David (qidawei@xiaomi.com)
 */
public enum CateRefType {
	articleCateId(1), // 与文章类别id关联
	//
	;

	private int code;

	private CateRefType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
