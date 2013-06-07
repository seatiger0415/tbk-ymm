package com.tbk.ymm.data.catcher.dao.ymm;

import java.util.Map;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.tbk.ymm.data.catcher.commons.enums.YmmConstEnum;
import com.tbk.ymm.data.catcher.commons.model.YmmConst;

@DAO
public interface YmmConstDAO {

	@SQL("SELECT id, const_value, description, update_time FROM " + YmmConst.TABLE)
	public Map<Integer, YmmConst> getAll();

	@SQL("UPDATE " + YmmConst.TABLE + " SET const_value = :2 WHERE id = :1.id")
	public void updateById(YmmConstEnum ymmFavItem, String constValue);

}
