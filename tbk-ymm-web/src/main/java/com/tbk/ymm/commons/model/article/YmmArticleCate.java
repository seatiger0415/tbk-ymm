package com.tbk.ymm.commons.model.article;

import java.util.Date;

/**
 * 文章类目
 * 
 * @author David (qidawei@xiaomi.com)
 */
public class YmmArticleCate {

	public static final String TABLE = "ymm_article_cate";
	public static final int IS_P_YES = 1;
	public static final int IS_P_NO = -1;
	public static final int STATUS_NORMAL = 1;
	public static final int STATUS_DELETE = -1;
	//
	private int id;
	private String name;
	private int parentId;
	private int isParent = IS_P_NO;
	private int status = STATUS_NORMAL;
	//

	private Date createTime;
	private Date updateTime;

	// --------------------------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getIsParent() {
		return isParent;
	}

	public void setIsParent(int isParent) {
		this.isParent = isParent;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
