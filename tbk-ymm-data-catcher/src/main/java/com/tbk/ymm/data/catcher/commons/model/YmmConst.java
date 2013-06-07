package com.tbk.ymm.data.catcher.commons.model;

import java.util.Date;

/**
 * @author David (qidawei@xiaomi.com)
 */
public class YmmConst {

	public static final String TABLE = "ymm_const";

	private int id;
	private String constValue;
	private String description;
	private Date updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConstValue() {
		return constValue;
	}

	public void setConstValue(String constValue) {
		this.constValue = constValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
