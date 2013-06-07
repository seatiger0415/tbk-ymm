package com.tbk.ymm.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.tbk.ymm.commons.model.YmmRecommendEachNavigation;

/**
 * @author David (qidawei@xiaomi.com)
 */
@DAO
public interface YmmRecommendEachNavigationDAO {

	@SQL("SELECT * FROM " + YmmRecommendEachNavigation.TABLE + " WHERE navigation_id = :1")
	public List<YmmRecommendEachNavigation> getByNavigationId(long navId);

}
