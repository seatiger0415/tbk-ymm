package com.tbk.ymm.dao.article;

import java.util.List;
import java.util.Map;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.tbk.ymm.commons.model.article.YmmArticleCate;

/**
 * @author David (qidawei@xiaomi.com)
 */
@DAO
public interface YmmArticleCateDAO {

	public static final String FEILDS = "id, name, parent_id, is_parent, status, create_time, update_time";

	@SQL("SELECT " + FEILDS + " FROM " + YmmArticleCate.TABLE + " WHERE parent_id = :1")
	public List<YmmArticleCate> getSubList(int id);

	@SQL("SELECT " + FEILDS + " FROM " + YmmArticleCate.TABLE + " WHERE id = :1")
	public YmmArticleCate getById(int id);

	@SQL("SELECT " + FEILDS + " FROM " + YmmArticleCate.TABLE)
	public Map<Integer, YmmArticleCate> getAllMap();

}
