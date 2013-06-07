package com.tbk.ymm.service;

import java.util.List;

import com.tbk.ymm.commons.dto.YmmHomeDataOneCate;
import com.tbk.ymm.commons.model.YmmNavigationCate;

/**
 * 孕妈妈首页service；因为首页聚合了很多内容，所以单独做一个service
 * 
 * @author David (qidawei@xiaomi.com)
 */
public interface YmmHomeService {

	/**
	 * 获取首页主体部分展示的数据
	 * 
	 * @param navigationList
	 *            已经取到的导航数据
	 * @return
	 */
	public List<YmmHomeDataOneCate> getHomeData(List<YmmNavigationCate> navigationList);
}
