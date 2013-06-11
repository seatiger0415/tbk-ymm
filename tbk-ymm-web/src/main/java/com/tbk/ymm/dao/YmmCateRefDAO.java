package com.tbk.ymm.dao;

import java.util.List;
import java.util.Map;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.tbk.ymm.commons.enums.CateRefType;
import com.tbk.ymm.commons.model.YmmCateRef;

/**
 * @author David (qidawei@xiaomi.com)
 */
@DAO
public interface YmmCateRefDAO {

	public static final String FIELDS = "id, cate_id, article_cate_id, type";
	public static final String FIELDS_1 = "cate_id, article_cate_id, type";

	@SQL("SELECT " + FIELDS + " FROM " + YmmCateRef.TABLE + " WHERE cate_id = :1 AND type = :2.code")
	public List<YmmCateRef> getListByCateIdAndType(long cateId, CateRefType type);

	@SQL("SELECT " + FIELDS_1 + " FROM " + YmmCateRef.TABLE + " WHERE type = :1.code")
	public Map<Long, YmmCateRef> getAllMapByCateIdAndType(CateRefType type);

}
