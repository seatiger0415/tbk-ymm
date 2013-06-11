package com.tbk.ymm.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.tbk.ymm.data.catcher.commons.enums.YmmFavoriteItemTable;
import com.tbk.ymm.data.catcher.commons.model.YmmFavoriteItem;

/**
 * @author David (qidawei@xiaomi.com)
 */
@DAO
public interface YmmFavoriteItemDAO {

	/**
	 * TODO 这个查询效率比较低
	 * 
	 * @param favItemTable
	 * @param cidList
	 * @param offset
	 * @param num
	 * @return
	 */
	@SQL("SELECT * FROM ##(:1.tableName) WHERE cid IN (:2) AND item_price >= :3 AND item_price <= :4 " +
			"ORDER BY sell_count DESC LIMIT :5,:6")
	public List<YmmFavoriteItem> getByCidListAndPrice(YmmFavoriteItemTable favItemTable,
			List<Long> cidList, int smallPrice, int bigPrice, int offset, int num);

	@SQL("SELECT * FROM ##(:1.tableName) WHERE cid IN (:2) ORDER BY sell_count DESC LIMIT :3,:4")
	public List<YmmFavoriteItem> getByCidList(YmmFavoriteItemTable favItemTable,
			List<Long> cidList, int offset, int num);

	@SQL("SELECT count(*) FROM ##(:1.tableName) WHERE cid IN (:2) AND item_price >= :3 AND item_price <= :4")
	public int getCountByCidListAndPrice(YmmFavoriteItemTable favItemTable, List<Long> cidList,
			int smallPrice, int bigPrice);

	@SQL("SELECT count(*) FROM ##(:1.tableName) WHERE cid IN (:2)")
	public int getCountByCidList(YmmFavoriteItemTable favItemTable, List<Long> cidList);

	@SQL("SELECT * FROM ##(:1.tableName) WHERE track_iid IN (:2)")
	public List<YmmFavoriteItem> getByTrackIidList(YmmFavoriteItemTable favItemTable, List<String> trackIidList);

}
