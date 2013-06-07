package com.tbk.ymm.data.catcher.utils;

/**
 * 将trackIid转化成numIid，这不是平台推荐的方式，因为trackIid的生成方式随时可能变更。<br/>
 * 但这个功能有时特别重要。比如：<br/>
 * 1. 根据“类别推荐”接口返回的商品，再次抓取商品推荐相关的商品，需要numIid；
 * 2. 上面两个抓取接口，抓到的数据有重复，但是二者trackIid不一样，需要在db中存numIid并根据numIid去重。
 * 
 * @author David (qidawei@xiaomi.com)
 */
public class TrackIidToNumIidUtil {

	/**
	 * 从trackIid中解析出itemId；这不是平台推荐的方式，因为trackIid随时可能会升级
	 * 
	 * @param trackIid
	 * @return
	 * @throws Exception
	 */
	public static long parseNumIidFromTrackIid(String trackIid) throws Exception {
		if (null == trackIid) {
			throw new Exception("getItemIdFromTrackIid.trackIid is null");
		}
		String[] splitArr = trackIid.split("_");
		//
		return Long.parseLong(splitArr[0]);
	}
}
