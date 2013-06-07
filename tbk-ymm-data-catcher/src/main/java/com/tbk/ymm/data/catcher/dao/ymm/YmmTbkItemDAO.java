package com.tbk.ymm.data.catcher.dao.ymm;

import java.util.Date;
import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.tbk.ymm.data.catcher.commons.enums.YmmTbkItemTable;
import com.tbk.ymm.data.catcher.commons.model.YmmTbkItem;

/**
 * @author David (qidawei@xiaomi.com)
 */
@DAO
public interface YmmTbkItemDAO {

	// 怎么才能把表名搞到注解中呢:##(:1)

	public static final String INSERT_F = "cid, num_iid, title, nick, pic_url, price, click_url, " +
			"commission, commission_rate, commission_num, commission_volume, shop_click_url, " +
			"seller_credit_score, item_location, volume, create_time, update_time, promotion_price, " +
			"taobaoke_cat_click_url";

	@SQL("SELECT COUNT(*) FROM ##(:2.tableName) WHERE update_time >= :1")
	public int getCountAfterOneTime(Date begin, YmmTbkItemTable itemTable);

	// ------------------------------------------------

	@SQL("INSERT IGNORE INTO ##(:2.tableName) (" + INSERT_F + ") VALUES (:1.cid, :1.numIid, :1.title," +
			" :1.nick, :1.picUrl, :1.price, :1.clickUrl, :1.commission, :1.commissionRate," +
			" :1.commissionNum, :1.commissionVolume, :1.shopClickUrl, :1.sellerCreditScore," +
			" :1.itemLocation, :1.volume, :1.createTime, :1.updateTime, :1.promotionPrice," +
			" :1.taobaokeCatClickUrl)")
	public int insertIgnore(List<YmmTbkItem> itemList, YmmTbkItemTable itemTable);

	@SQL("DELETE FROM ##(:2.tableName) WHERE cid = :1")
	public void deleteByCid(long cid, YmmTbkItemTable itemTable);

}
