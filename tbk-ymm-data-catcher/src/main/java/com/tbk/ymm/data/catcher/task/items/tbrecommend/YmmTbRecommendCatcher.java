package com.tbk.ymm.data.catcher.task.items.tbrecommend;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.FavoriteItem;
import com.taobao.api.request.CategoryrecommendItemsGetRequest;
import com.taobao.api.request.ItemrecommendItemsGetRequest;
import com.taobao.api.response.CategoryrecommendItemsGetResponse;
import com.taobao.api.response.ItemrecommendItemsGetResponse;
import com.tbk.ymm.data.catcher.commons.consts.YmmDataConsts;
import com.tbk.ymm.data.catcher.commons.enums.YmmConstEnum;
import com.tbk.ymm.data.catcher.commons.enums.YmmFavoriteItemTable;
import com.tbk.ymm.data.catcher.commons.model.YmmConst;
import com.tbk.ymm.data.catcher.commons.model.YmmFavoriteItem;
import com.tbk.ymm.data.catcher.dao.ymm.YmmConstDAO;
import com.tbk.ymm.data.catcher.dao.ymm.YmmFavoriteItemDAO;
import com.tbk.ymm.data.catcher.dao.ymm.YmmItemCateDAO;
import com.tbk.ymm.data.catcher.logic.YmmConstLogic;
import com.tbk.ymm.data.catcher.utils.RoseBeanFactory;
import com.tbk.ymm.data.catcher.utils.TrackIidToNumIidUtil;

/**
 * taobao.categoryrecommend.items.get 和taobao.itemrecommend.items.get ： <br/>
 * 分别是 1. 根据类目信息推荐相关联的宝贝集 和 2. 根据商品推荐相关联的商品两个推荐方式；<br/>
 * 这两个接口的本意是突出“相关性、推荐”的，例如淘宝店里的“还浏览了。。。”的推荐，这里作为商品库建设的一部分，算是用偏门了
 * 
 * @author David (qidawei@xiaomi.com)
 */
public class YmmTbRecommendCatcher {

	private final YmmItemCateDAO ymmItemCateDAO = RoseBeanFactory.getBean(YmmItemCateDAO.class);
	private final YmmFavoriteItemDAO ymmFavoriteItemDAO = RoseBeanFactory.getBean(YmmFavoriteItemDAO.class);
	private final YmmConstLogic ymmConstLogic = RoseBeanFactory.getBean(YmmConstLogic.class);
	private final YmmConstDAO ymmConstDAO = RoseBeanFactory.getBean(YmmConstDAO.class);
	//
	private static final long CATE_RECOMMEND_TYPE = 1L; // 请求类型，1：类目下热门商品推荐。其他值当非法值处理
	private static final long ITEM_RECOMMEND_TYPE = 1L; // 查询类型标识符，可传入1-3，1：同类商品推荐，2：异类商品推荐， 3：同店商品推荐。其他值当非法值处理
	private static final long CATE_RECOMMEND_NUM_ONCE = 20L; // 请求个数，建议获取20个，API好像最多返回50个
	private static final long ITEM_RECOMMEND_NUM_ONCE = 10L; // 请求个数，建议获取20个，API好像最多返回50个

	/**
	 * 抓取所有孕妇相关叶子节点的推荐的关联商品
	 */
	public void catchAllItems() {
		Map<YmmConstEnum, YmmConst> constMap = ymmConstLogic.getAll();
		YmmConst ymmConst = constMap.get(YmmConstEnum.Ymm_fav_item);
		if (null == ymmConst) {
			System.out.println("YmmTbRecommendCatcher.ymmConst is null");
			return;
		}
		YmmFavoriteItemTable curFavItemTable = YmmFavoriteItemTable.getEnumByTableName(ymmConst.getConstValue());
		if (null == curFavItemTable) {
			System.out.println("YmmTbRecommendCatcher.enum itemTable is null.tableName:" + ymmConst.getConstValue());
			return;
		}
		// 1. 先知道要更新哪个表；2.删除旧数据；3.更新；4.切换当前cid的查询到更新过的表
		YmmFavoriteItemTable updateYmmFavItemTable = YmmFavoriteItemTable.getTheOther(curFavItemTable);
		//
		Date begin = new Date();
		List<Long> allleafCids = getAllLeafCids();
		int count = 1;
		for (Long cid : allleafCids) {
			List<YmmFavoriteItem> itemList = getItemFromApi(cid);
			if (itemList.isEmpty()) {
				continue;
			}
			//
			ymmFavoriteItemDAO.deleteByCid(cid, updateYmmFavItemTable);
			ymmFavoriteItemDAO.insertOrUpdate(itemList, updateYmmFavItemTable);
			//
			System.out.println("YmmTbRecommendCatcher.catch one cate.index: " + count++ + ";cateId: " + cid
					+ "; resultSize: " + itemList.size());
			try {
				Thread.sleep(2000L);
			} catch (InterruptedException e1) {
			}
		}
		//
		if (checkNewData(begin, updateYmmFavItemTable)) {
			ymmConstDAO.updateById(YmmConstEnum.Ymm_fav_item, updateYmmFavItemTable.getTableName());
		}
	}

	/**
	 * @param begin
	 * @param updateYmmFavItemTable
	 * @return
	 */
	private boolean checkNewData(Date begin, YmmFavoriteItemTable updateYmmFavItemTable) {
		int count = ymmFavoriteItemDAO.getCountAfterOneTime(begin, updateYmmFavItemTable);
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 获取所有的末端分类
	 * 
	 * @return
	 */
	private List<Long> getAllLeafCids() {
		return ymmItemCateDAO.getAllLeafCids();
	}

	private List<YmmFavoriteItem> getItemFromApi(long cid) {
		CategoryrecommendItemsGetResponse response = getCategoryrecommendItemsResponseByHttp(cid, 3);
		String errorCode = response.getErrorCode();
		List<FavoriteItem> itemList = response.getFavoriteItems();
		if (null != errorCode || null == itemList || itemList.isEmpty()) {
			System.out.println("errorCode:" + errorCode + ";body:" + response.getBody());
			return Collections.emptyList();
		}
		//
		System.out.println("YmmTbRecommendCatcher.item from cate recommend.size:" + itemList.size());
		//
		List<FavoriteItem> itemRecommendList = Lists.newArrayListWithExpectedSize(itemList.size()
				* (int) ITEM_RECOMMEND_NUM_ONCE);
		for (FavoriteItem favoriteItem : itemList) {
			long itemId = 0;
			try {
				itemId = TrackIidToNumIidUtil.parseNumIidFromTrackIid(favoriteItem.getTrackIid());
				ItemrecommendItemsGetResponse res = getItemrecommendItemsResponseByHttp(itemId, 3);
				List<FavoriteItem> innerItemList = res.getValues();
				if (null != innerItemList && !innerItemList.isEmpty()) {
					itemRecommendList.addAll(innerItemList);
				}
				Thread.sleep(500L);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//
		itemList.addAll(itemRecommendList);
		//
		System.out.println("YmmTbRecommendCatcher.all item recommend of one cate.size:" + itemList.size());
		//
		return getYmmFavItemList(cid, itemList);
	}

	/**
	 * 根据API返回的List<FavoriteItem组装出网站呢的YmmFavoriteItem列表
	 */
	private List<YmmFavoriteItem> getYmmFavItemList(long cid, List<FavoriteItem> itemList) {
		List<YmmFavoriteItem> resultList = Lists.newArrayListWithExpectedSize(itemList.size());
		for (FavoriteItem favoriteItem : itemList) {
			try {
				YmmFavoriteItem item = YmmFavoriteItem.getInstanceFromFavItem(favoriteItem);
				if (null == item) {
					continue;
				}
				item.setCid(cid);
				resultList.add(item);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

	/**
	 * 通过API，获取类目相关推荐，带重试功能
	 * 
	 * @param cid
	 * @param maxRetryTimes
	 * @return
	 */
	private CategoryrecommendItemsGetResponse getCategoryrecommendItemsResponseByHttp(long cid, int maxRetryTimes) {
		CategoryrecommendItemsGetResponse response = null;
		try {
			TaobaoClient client = new DefaultTaobaoClient(YmmDataConsts.TB_API_URL,
					YmmDataConsts.YMM_APP_KEY,
					YmmDataConsts.YMM_APP_SECRET);
			CategoryrecommendItemsGetRequest req = new CategoryrecommendItemsGetRequest();
			req.setCategoryId(cid);
			req.setRecommendType(CATE_RECOMMEND_TYPE);
			req.setCount(CATE_RECOMMEND_NUM_ONCE);
			// req.setExt("额外参数");
			response = client.execute(req);
		} catch (Exception e) {
			e.printStackTrace();
			if (maxRetryTimes > 0) {
				try {
					Thread.sleep(2000L);
				} catch (InterruptedException e1) {
				}
				//
				response = getCategoryrecommendItemsResponseByHttp(cid, maxRetryTimes - 1);
			}
		}
		return response;
	}

	/**
	 * 通过API获取商品相关的推荐，带重试功能
	 * 
	 * @param itemId
	 * @param maxRetryTimes
	 * @return
	 */
	private ItemrecommendItemsGetResponse getItemrecommendItemsResponseByHttp(long itemId, int maxRetryTimes) {
		ItemrecommendItemsGetResponse response = null;
		try {
			TaobaoClient client = new DefaultTaobaoClient(YmmDataConsts.TB_API_URL,
					YmmDataConsts.YMM_APP_KEY,
					YmmDataConsts.YMM_APP_SECRET);
			ItemrecommendItemsGetRequest req = new ItemrecommendItemsGetRequest();
			req.setItemId(itemId);
			req.setRecommendType(ITEM_RECOMMEND_TYPE);
			req.setCount(ITEM_RECOMMEND_NUM_ONCE);
			// req.setExt("123456");
			response = client.execute(req);
		} catch (Exception e) {
			e.printStackTrace();
			if (maxRetryTimes > 0) {
				try {
					Thread.sleep(2000L);
				} catch (InterruptedException e1) {
				}
				//
				response = getItemrecommendItemsResponseByHttp(itemId, maxRetryTimes - 1);
			}
		}
		return response;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		YmmTbRecommendCatcher tmmTbRecommendByCateCatcher = new YmmTbRecommendCatcher();
		tmmTbRecommendByCateCatcher.catchAllItems();
	}

}
