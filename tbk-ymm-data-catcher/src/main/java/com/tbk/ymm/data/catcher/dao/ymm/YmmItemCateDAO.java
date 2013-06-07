package com.tbk.ymm.data.catcher.dao.ymm;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.tbk.ymm.data.catcher.commons.model.YmmItemCate;

@DAO
public interface YmmItemCateDAO {

	@SQL("SELECT cid FROM " + YmmItemCate.TABLE + " WHERE is_parent = " + YmmItemCate.IS_P_NO)
	public List<Long> getAllLeafCids();

	// --------------------------------

	@SQL("INSERT INTO "
			+ YmmItemCate.TABLE
			+ "(cid, name, is_parent, parent_cid, sort_order, status, create_time, update_time) "
			+ " VALUES(:1.cid, :1.name, :1.isParent, :1.parentCid, :1.sortOrder, :1.status, :1.createTime, :1.updateTime)"
			+ " ON DUPLICATE KEY UPDATE name = VALUES(name), is_parent = VALUES(is_parent), parent_cid = VALUES(parent_cid)"
			+ ", sort_order = VALUES(sort_order), status = VALUES(status), update_time = VALUES(update_time)")
	public void insertOrUpdate(List<YmmItemCate> itemCateList);

}
