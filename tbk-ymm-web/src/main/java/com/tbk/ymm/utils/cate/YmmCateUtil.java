package com.tbk.ymm.utils.cate;

import com.tbk.ymm.commons.consts.YmmConsts;

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

	/**
	 * @param articleCateId
	 * @return
	 */
	public static int getLv2ArticleCateId(int articleCateId) {
		switch (articleCateId) {
			case YmmConsts.SHOPPING_CATE_ID :
				return YmmConsts.SHOPPING_DEF_LV2_CATE_ID;
			case YmmConsts.EXPERIENCE_CATE_ID :
				return YmmConsts.EXPERIENCE_DEF_LV2_CATE_ID;
			default :
				return articleCateId;
		}
	}

}
