package com.tbk.ymm.web.controllers.test;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;

import com.tbk.ymm.commons.model.YmmTest;
import com.tbk.ymm.dao.YmmTestDAO;

@Path("")
public class YmmTestController {

	@Autowired
	private YmmTestDAO ymmTestDAO;

	@Get("/hello")
	public String getHello(@Param("id") int id) {
		YmmTest ymmTest = ymmTestDAO.getById(id);
		if (null != ymmTest) {
			return "@" + ymmTest.getName();
		} else {
			return "@no result";
		}
	}

	@Get("/helloPage")
	public String getHelloPage(Invocation inv, @Param("id") int id) {
		YmmTest ymmTest = ymmTestDAO.getById(id);
		//
		inv.addModel("id", ymmTest.getId());
		inv.addModel("name", ymmTest.getName());
		//
		return "ymm_test";
	}
}
