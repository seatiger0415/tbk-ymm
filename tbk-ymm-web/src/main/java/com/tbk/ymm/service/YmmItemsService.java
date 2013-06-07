package com.tbk.ymm.service;

import com.tbk.ymm.commons.dto.ResultView;
import com.tbk.ymm.commons.dto.YmmItem;
import com.tbk.ymm.commons.dto.params.YmmItemQueryParam;

/**
 * 商品查询相关的service；脚本抓取的商品可能有多重来源，目前有： <br/>
 * 1. 商品关联推荐 接口中的 taobao.categoryrecommend.items.get 根据类目信息推荐相关联的宝贝集 <br/>
 * 2. 淘宝客接口（目前这个API还不知道能不能用） <br/>
 * 3. ... <br/>
 * <br/>
 * 这个Service要实现可配置式的从以上多个数据源中获取数据，并对上层透明
 * 
 * @author David (qidawei@xiaomi.com)
 */
public interface YmmItemsService {

	/**
	 * 根据二级类别id列表查询商品：分页，排序
	 * 
	 * @param param
	 * @return
	 */
	public ResultView<YmmItem> getByLv2CidListPagedInOrder(YmmItemQueryParam param);

}
