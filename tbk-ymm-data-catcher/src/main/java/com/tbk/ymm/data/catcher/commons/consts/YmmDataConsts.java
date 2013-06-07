package com.tbk.ymm.data.catcher.commons.consts;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @author David (qidawei@xiaomi.com)
 */
public class YmmDataConsts {

	// 调用API的参数
	public static final String TB_API_URL = "http://gw.api.taobao.com/router/rest";
	public static final String YMM_APP_KEY = "21522531";
	public static final String YMM_APP_SECRET = "5a38ed4274d0459d5141a3087d4131c8";
	//
	// 阿里妈妈账号
	public static final long MY_PID = 34998088L; // 用户的pid,必须是mm_xxxx_0_0这种格式中间的"xxxx"
	public static final String MY_NICK_NAME = "seatiger0415";

	// --------------------begin 类目id--------------------------------------------------

	// 孕妇用品顶级category id (孕妇装/孕产妇用品/营养)
	public static final long YUN_TOP_CID = 50022517L;
	// 二级类目
	public static final long YUN_FANGFUSHE_CID = 50012374L;// 防辐射 category id
	public static final long YUN_YUNFUZHUANG_CID = 50012354L; // 孕妇装
	public static final long YUN_NEIYI_CID = 50016687L; // 内衣 哺乳文胸/内裤/产检裤
	public static final long YUN_XIHU_CID = 50026457L; // 洗护用品 孕产妇护肤/洗护/祛纹
	public static final long YUN_XIEWAMAO_CID = 50012374L;// 产妇帽/孕妇袜/孕妇鞋
	public static final long YUN_YINGYANG_CID = 50026460L; // 孕产妇营养品
	public static final long YUN_YINGYANG_YUEZI_CID = 50026460L; // 月子营养品
	public static final long YUN_SUSHEN_CID = 50023660L; // 束缚带/产妇瘦身塑体衣/盆骨矫正带
	public static final long YUN_JIAJU_BURU_CID = 50023613L; // 家居服/哺乳装/秋衣裤
	public static final long YUN_CHANQIANHOU_CID = 50006000L; // 妈妈产前产后用品
	//
	public static final List<Long> ALL_CATE2_CIDS = Lists.newArrayList(YUN_FANGFUSHE_CID,
			YUN_YUNFUZHUANG_CID, YUN_NEIYI_CID, YUN_XIHU_CID, YUN_XIEWAMAO_CID, YUN_YINGYANG_CID,
			YUN_YINGYANG_YUEZI_CID, YUN_SUSHEN_CID, YUN_JIAJU_BURU_CID, YUN_CHANQIANHOU_CID);

	public static final Set<Long> filterSet = Sets.newHashSet(50002664L/* 孕妇装-其他 */,
			50023595L/* 孕妇装-影楼服装 */, 50023594L/* 孕妇装-棉衣羽绒服大衣 */, 50050346L/* 孕妇装-孕妇婚纱礼服 */
			);

	// ----------------end 类目id------------------------------------------------------
	/**
	 * credit 参数：
	 * 1heart(一心) 2heart (两心) 3heart(三心) 4heart(四心) 5heart(五心) 1diamond(一钻) 2diamond(两钻) 3diamond(三钻) 4diamond(四钻)
	 * 5diamond(五钻) 1crown(一冠) 2crown(两冠) 3crown(三冠) 4crown(四冠) 5crown(五冠) 1goldencrown(一黄冠) 2goldencrown(二黄冠)
	 * 3goldencrown(三黄冠) 4goldencrown(四黄冠) 5goldencrown(五黄冠)
	 */
	/**
	 * Sort参数，排序方式：
	 * 默认排序:default price_desc(价格从高到低) price_asc(价格从低到高) credit_desc(信用等级从高到低) commissionRate_desc(佣金比率从高到低)
	 * commissionRate_asc(佣金比率从低到高) commissionNum_desc(成交量成高到低) commissionNum_asc(成交量从低到高)
	 * commissionVolume_desc(总支出佣金从高到低) commissionVolume_asc(总支出佣金从低到高) delistTime_desc(商品下架时间从高到低)
	 * delistTime_asc(商品下架时间从低到高)
	 */

}
