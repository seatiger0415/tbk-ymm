package com.tbk.ymm.commons.model;

import com.tbk.ymm.commons.enums.CateRefType;

/**
 * ymm_cate的扩展属性字段表
 * 
 * @author David (qidawei@xiaomi.com)
 */
public class YmmCateRef {

	public static final String TABLE = "ymm_cate_ref";

	private int id;
	private long cateId; // 商品类目id， 目前只指导航id；
	private int type = CateRefType.articleCateId.getCode(); //
	private int articleCateId;// 文章类目id

	//
	// 待扩展

	// ---------------------------------------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 商品类目id， 目前只指导航id
	 * 
	 * @return
	 */
	public long getCateId() {
		return cateId;
	}

	public void setCateId(long cateId) {
		this.cateId = cateId;
	}

	public int getArticleCateId() {
		return articleCateId;
	}

	public void setArticleCateId(int articleCateId) {
		this.articleCateId = articleCateId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
