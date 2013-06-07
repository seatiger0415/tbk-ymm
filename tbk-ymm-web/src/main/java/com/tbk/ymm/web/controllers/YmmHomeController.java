package com.tbk.ymm.web.controllers;

import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;

import com.tbk.ymm.commons.dto.YmmHomeDataOneCate;
import com.tbk.ymm.commons.model.YmmNavigationCate;
import com.tbk.ymm.service.YmmCateService;
import com.tbk.ymm.service.YmmHomeService;

@Path("")
public class YmmHomeController {

	@Autowired
	private YmmCateService ymmCateService;
	@Autowired
	private YmmHomeService ymmHomeService;

	@Get("")
	public String home(Invocation inv) {
		List<YmmNavigationCate> navigationList = ymmCateService.getNavigationCateList();
		//
		List<YmmHomeDataOneCate> homeDataList = ymmHomeService.getHomeData(navigationList);
		//
		inv.addModel("isHome", true);
		inv.addModel("navigationList", navigationList);
		inv.addModel("homeDataList", homeDataList);
		//
		return "ymm_home";
	}

}
