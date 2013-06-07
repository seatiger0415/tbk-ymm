package com.tbk.ymm.dao.cate;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.tbk.ymm.data.catcher.commons.model.YmmItemCate;

@DAO
public interface YmmItemCateDAO {

	@SQL("SELECT * FROM " + YmmItemCate.TABLE + " WHERE parent_cid = :1 AND status = 1 ORDER BY sort_order")
	public List<YmmItemCate> getSubItemCateList(long parentCid);

	@SQL("SELECT parent_cid FROM " + YmmItemCate.TABLE + " WHERE cid = :1")
	public Long getParentCid(long cid);

	@SQL("SELECT * FROM " + YmmItemCate.TABLE + " WHERE cid IN (:1) AND status = 1  ORDER BY sort_order")
	public List<YmmItemCate> getByCids(List<Long> cidlist);

	@SQL("SELECT cid FROM " + YmmItemCate.TABLE + " WHERE is_parent = 1 AND status = 1")
	public Set<Long> getAllLv1CidSet();

	@SQL("SELECT cid, name, parent_cid, is_parent, sort_order FROM " + YmmItemCate.TABLE
			+ " WHERE is_parent = -1 AND status = 1")
	public Map<Long, YmmItemCate> getAllLv2CateMap();

}
