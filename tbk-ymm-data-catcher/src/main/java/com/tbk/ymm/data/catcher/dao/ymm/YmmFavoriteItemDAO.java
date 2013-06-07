package com.tbk.ymm.data.catcher.dao.ymm;

import java.util.Date;
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

	@SQL("SELECT count(*) FROM ##(:2.tableName) WHERE update_time >= :1")
	public int getCountAfterOneTime(Date begin, YmmFavoriteItemTable updateYmmFavItemTable);

	// ------------------------------------------------

	@SQL("INSERT INTO ##(:2.tableName) (cid, track_iid, num_iid, item_name, item_picture, item_url, item_price,"
			+ " promotion_price, sell_count, create_time, update_time) "
			+ " VALUES(:1.cid, :1.trackIid, :1.numIid, :1.itemName, :1.itemPicture, :1.itemUrl, :1.itemPrice, :1.promotionPrice, "
			+ ":1.sellCount, :1.createTime, :1.updateTime)"
			+ " ON DUPLICATE KEY UPDATE cid = VALUES(cid), item_name = VALUES(item_name), item_picture = VALUES(item_picture),"
			+ " item_url = VALUES(item_url), item_price = VALUES(item_price), promotion_price = VALUES(promotion_price),"
			+ " sell_count = VALUES(sell_count), update_time = VALUES(update_time)")
	public void insertOrUpdate(List<YmmFavoriteItem> itemList, YmmFavoriteItemTable favItemTable);

	@SQL("DELETE FROM ##(:2.tableName) WHERE cid = :1")
	public void deleteByCid(long cid, YmmFavoriteItemTable favItemTable);

}
