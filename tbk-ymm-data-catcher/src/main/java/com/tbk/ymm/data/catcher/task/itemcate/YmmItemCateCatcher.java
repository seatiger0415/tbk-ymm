package com.tbk.ymm.data.catcher.task.itemcate;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.ItemCat;
import com.taobao.api.request.ItemcatsGetRequest;
import com.taobao.api.response.ItemcatsGetResponse;
import com.tbk.ymm.data.catcher.commons.consts.YmmDataConsts;
import com.tbk.ymm.data.catcher.commons.model.YmmItemCate;
import com.tbk.ymm.data.catcher.dao.ymm.YmmItemCateDAO;
import com.tbk.ymm.data.catcher.utils.RoseBeanFactory;

/**
 * 抓取淘宝商品的分类信息
 * 
 * @author David (qidawei@xiaomi.com)
 */
public class YmmItemCateCatcher {

	private static final int RETRY_TIMES = 3;
	private static YmmItemCateDAO ymmItemCateDAO = RoseBeanFactory.getBean(YmmItemCateDAO.class);

	/**
	 * ItemCat: {"cid":50022517,"is_parent":true,"name":"孕妇装\/孕产妇用品\/营养","parent_cid":0},
	 * 
	 * @throws ApiException
	 */
	public void catchAllCateInfo(long cid) {
		List<YmmItemCate> itemCateList = getCateList(cid);
		if (itemCateList.isEmpty()) {
			return;
		}
		try {
			ymmItemCateDAO.insertOrUpdate(itemCateList);
			System.out.println("YmmItemCateCatcher.catchAllCateInfo.suc.cid:" + cid);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//
		for (YmmItemCate ymmItemCate : itemCateList) {
			Long curCid = ymmItemCate.getCid();
			Integer isParent = ymmItemCate.getIsParent();
			if (null != isParent && isParent.equals(YmmItemCate.IS_P_YES) && null != curCid && curCid > 0) {
				catchAllCateInfo(curCid);
			}
		}
		//
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 * @throws ApiException
	 */
	private List<YmmItemCate> getCateList(long cid) {
		ItemcatsGetResponse response = getResponseByHttp(cid, RETRY_TIMES);
		//
		String resultStr = response.getBody();
		String errorCode = response.getErrorCode();
		List<ItemCat> itemList = response.getItemCats();
		if (null != errorCode || resultStr.contains("error_response") || null == itemList || itemList.isEmpty()) {
			System.out.println("errorCode:" + errorCode);
			return Collections.emptyList();
		}
		List<YmmItemCate> resultList = Lists.newArrayListWithExpectedSize(itemList.size());
		for (ItemCat itemCat : itemList) {
			if (null != itemCat && !isInFilterList(itemCat.getCid())) {
				resultList.add(YmmItemCate.getInstanceFromItemCat(itemCat));
			}
		}
		return resultList;
	}

	/**
	 * 是否在过滤列表中，有一些不好的分类，需要ban掉
	 * 
	 * @param cid
	 * @return
	 */
	private boolean isInFilterList(Long cid) {
		if (YmmDataConsts.filterSet.contains(cid)) {
			return true;
		}
		return false;
	}

	/**
	 * 带重试功能
	 * 
	 * @param cid
	 * @param maxRetryTimes
	 * @return
	 */
	private ItemcatsGetResponse getResponseByHttp(long cid, int maxRetryTimes) {
		ItemcatsGetResponse response = null;
		try {
			TaobaoClient client = new DefaultTaobaoClient(YmmDataConsts.TB_API_URL,
					YmmDataConsts.YMM_APP_KEY,
					YmmDataConsts.YMM_APP_SECRET);
			ItemcatsGetRequest req = new ItemcatsGetRequest();
			req.setFields("cid,parent_cid,name,is_parent,status,sort_order");
			req.setParentCid(cid);
			response = client.execute(req);
		} catch (Exception e) {
			e.printStackTrace();
			if (maxRetryTimes > 0) {
				try {
					Thread.sleep(2000L);
				} catch (InterruptedException e1) {
				}
				//
				response = getResponseByHttp(cid, maxRetryTimes - 1);
			}
		}
		return response;
	}

	/**
	 * @param args
	 * @throws ApiException
	 */
	public static void main(String[] args) throws ApiException {
		YmmItemCateCatcher ymmCateInfoCatcher = new YmmItemCateCatcher();
		ymmCateInfoCatcher.catchAllCateInfo(YmmDataConsts.YUN_TOP_CID);
	}
}
