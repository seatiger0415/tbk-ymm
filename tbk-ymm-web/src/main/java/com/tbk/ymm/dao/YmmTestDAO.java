package com.tbk.ymm.dao;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.tbk.ymm.commons.model.YmmTest;

@DAO
public interface YmmTestDAO {

	@SQL("SELECT id, name FROM ymm_test WHERE id = :1")
	public YmmTest getById(int id);
	
}
