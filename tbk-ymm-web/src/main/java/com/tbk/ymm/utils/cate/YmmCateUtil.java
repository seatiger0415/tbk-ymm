package com.tbk.ymm.utils.cate;

/**
 * @author David (qidawei@xiaomi.com)
 */
public class YmmCateUtil {

	private static final int NAVI_CATE_ID_BASE = 1000000000;
	private static final long NAVI_CATE_ID_AREA = 1000L;

	/**
	 * 判断一个cateId是否导航的特殊id，自定义的特殊导航id是大于NAVI_CATE_ID_BASE，
	 * 并小于的NAVI_CATE_ID_BASE + NAVI_CATE_ID_AREA的
	 * 
	 * @param cateId
	 * @return
	 */
	public static boolean isNavigationCate(long cateId) {
		if (cateId >= NAVI_CATE_ID_BASE && cateId < NAVI_CATE_ID_BASE + NAVI_CATE_ID_AREA) {
			return true;
		}
		return false;
	}

}
