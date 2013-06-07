package com.tbk.ymm.dao.cate;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.tbk.ymm.commons.model.YmmNavigationCate;

/**
 * @author David (qidawei@xiaomi.com)
 */
@DAO
public interface YmmNavigationCateDAO {

	public static final String FIELDS = "id, name, item_cate_ids, status";

	@SQL("SELECT " + FIELDS + " FROM " + YmmNavigationCate.TABLE + " WHERE status != " + YmmNavigationCate.STATUS_HIDE)
	public List<YmmNavigationCate> getAllOK();
}
