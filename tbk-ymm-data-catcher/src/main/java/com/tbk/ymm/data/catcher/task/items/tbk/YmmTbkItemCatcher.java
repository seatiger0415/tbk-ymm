package com.tbk.ymm.data.catcher.task.items.tbk;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TaobaokeItem;
import com.taobao.api.request.TaobaokeItemsGetRequest;
import com.taobao.api.response.TaobaokeItemsGetResponse;
import com.tbk.ymm.data.catcher.commons.consts.YmmDataConsts;
import com.tbk.ymm.data.catcher.commons.enums.YmmConstEnum;
import com.tbk.ymm.data.catcher.commons.enums.YmmTbkItemTable;
import com.tbk.ymm.data.catcher.commons.model.YmmConst;
import com.tbk.ymm.data.catcher.commons.model.YmmTbkItem;
import com.tbk.ymm.data.catcher.dao.ymm.YmmConstDAO;
import com.tbk.ymm.data.catcher.dao.ymm.YmmItemCateDAO;
import com.tbk.ymm.data.catcher.dao.ymm.YmmTbkItemDAO;
import com.tbk.ymm.data.catcher.logic.YmmConstLogic;
import com.tbk.ymm.data.catcher.utils.RoseBeanFactory;

/**
 * 通过淘宝客接口，抓取要推广的商品；主任务
 * 
 * @author David (qidawei@xiaomi.com)
 */
public class YmmTbkItemCatcher {

	private static final String FIELDS = "num_iid,title,nick,pic_url,price,click_url,commission," +
			"commission_rate,commission_num,commission_volume,shop_click_url,seller_credit_score," +
			"item_location,volume";
	private static final long PAGE_SIZE = 40L;

	private static final YmmTbkItemDAO ymmItemDAO = RoseBeanFactory.getBean(YmmTbkItemDAO.class);
	private final YmmConstLogic ymmConstLogic = RoseBeanFactory.getBean(YmmConstLogic.class);
	private final YmmConstDAO ymmConstDAO = RoseBeanFactory.getBean(YmmConstDAO.class);
	private static final YmmItemCateDAO ymmItemCateDAO = RoseBeanFactory.getBean(YmmItemCateDAO.class);

	/**
	 * 因为返回的数据中没有cid（类目）字段，所以请求时只能以叶子cid为条件，否则，自己站内不能区分商品的类目。 <br/>
	 * 注意：在插入一个类别的数据时，不要影响线上查询；
	 * 而且本次插入后，要删除上次结果中过期的数据；
	 * 
	 * =》 每次更新都全部删除旧数据，插入新数据；
	 * =》 方式：两个一样的表，更新那个当前不用的，更新完之后把对应类的查询切换到更新后的表
	 */
	public void catchAllItems() {
		Map<YmmConstEnum, YmmConst> constMap = ymmConstLogic.getAll();
		YmmConst ymmConst = constMap.get(YmmConstEnum.Ymm_tbk_item);
		if (null == ymmConst) {
			System.out.println("YmmTbRecommendCatcher.ymmConst is null");
			return;
		}
		YmmTbkItemTable curTbkItemTable = YmmTbkItemTable.getEnumByTableName(ymmConst.getConstValue());
		if (null == curTbkItemTable) {
			System.out.println("YmmTbRecommendCatcher.enum itemTable is null.tableName:" + ymmConst.getConstValue());
			return;
		}
		// 1. 先知道要更新哪个表；2.删除旧数据；3.更新；4.切换当前cid的查询到更新过的表
		YmmTbkItemTable updateYmmTbkItemTable = YmmTbkItemTable.getTheOther(curTbkItemTable);
		//
		Date begin = new Date();
		List<Long> allleafCids = getAllLeafCids();
		int count = 1;
		for (Long cid : allleafCids) {
			// 每个末端分类取120个
			List<YmmTbkItem> itemList = Lists.newArrayList();
			for (int pageNo = 1; pageNo <= 3; pageNo++) {
				List<YmmTbkItem> curItemList = getItemFromApi(cid, pageNo);
				itemList.addAll(curItemList);
			}
			if (itemList.isEmpty()) {
				System.out.println("YmmItemCatcher.catchAllItems.itemList is empty.cid:" + cid);
				continue;
			}
			//
			ymmItemDAO.deleteByCid(cid, updateYmmTbkItemTable);
			ymmItemDAO.insertIgnore(itemList, updateYmmTbkItemTable);
			System.out.println("YmmItemCatcher.catchAllItems.suc.count:" + count + "; cid:" + cid);
			count++;
		}
		if (checkNewData(begin, updateYmmTbkItemTable)) {
			ymmConstDAO.updateById(YmmConstEnum.Ymm_tbk_item, updateYmmTbkItemTable.getTableName());
		}
	}

	/**
	 * 校验一下更新是否成功
	 * 
	 * @param begin
	 * @param updateYmmTbkItemTable
	 * @return
	 */
	private boolean checkNewData(Date begin, YmmTbkItemTable updateYmmTbkItemTable) {
		int count = ymmItemDAO.getCountAfterOneTime(begin, updateYmmTbkItemTable);
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

	/**
	 * @param cid
	 * @param pageNo
	 *            页码 1-10
	 * @return
	 */
	private List<YmmTbkItem> getItemFromApi(long cid, int pageNo) {
		TaobaokeItemsGetResponse response = getResponseByHttp(cid, pageNo, 3);
		String errorCode = response.getErrorCode();
		List<TaobaokeItem> itemList = response.getTaobaokeItems();
		if (null != errorCode || null == itemList || itemList.isEmpty()) {
			System.out.println("errorCode:" + errorCode + ";body:" + response.getBody());
			return Collections.emptyList();
		}
		List<YmmTbkItem> resultList = Lists.newArrayListWithExpectedSize(itemList.size());
		for (TaobaokeItem tbkItem : itemList) {
			try {
				YmmTbkItem item = YmmTbkItem.getInstanceFromTBItem(tbkItem);
				item.setCid(cid);
				resultList.add(item);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

	/**
	 * 带重试功能
	 * 
	 * @param cid
	 * @param pageNo
	 *            页码 1-10
	 * @param maxRetryTimes
	 * @return
	 */
	private TaobaokeItemsGetResponse getResponseByHttp(long cid, long pageNo, int maxRetryTimes) {
		TaobaokeItemsGetResponse response = null;
		try {
			TaobaoClient client = new DefaultTaobaoClient(YmmDataConsts.TB_API_URL,
					YmmDataConsts.YMM_APP_KEY,
					YmmDataConsts.YMM_APP_SECRET);
			TaobaokeItemsGetRequest req = new TaobaokeItemsGetRequest();
			// 分页
			req.setPageNo(pageNo);
			req.setPageSize(PAGE_SIZE);
			//
			req.setFields(FIELDS);
			req.setNick(YmmDataConsts.MY_NICK_NAME);
			// req.setPid(YmmDataConsts.MY_PID);
			// req.setKeyword("abc");
			req.setCid(cid);
			req.setStartPrice("1");
			req.setEndPrice("99999");
			req.setAutoSend("true");

			req.setStartCredit("5heart");
			req.setEndCredit("5goldencrown");
			req.setSort("commissionNum_desc");
			// 佣金条件
			req.setStartCommissionRate("1000");
			req.setEndCommissionRate("9999");
			req.setStartCommissionNum("0");
			req.setEndCommissionNum("9999");
			//
			// 下面几个参数用来保证结果的质量
			req.setGuarantee("true");
			req.setStartTotalnum("100");// 成交量，这是个保证质量的重要条件
			req.setEndTotalnum("999999");
			req.setSevendaysReturn("true"); // 支持7天退换，保证质量的条件
			req.setRealDescribe("true");
			req.setReferType(1L);// 点击串跳转类型，1：单品，2：单品中间页？
			//
			//
			// req.setOnemonthRepair("true"); //30维修
			// req.setCashOndelivery("true"); //货到付款是否支持
			//
			// req.setArea("杭州");
			// req.setMallItem("true");
			// req.setCashCoupon("true"); //是否支持抵价券
			// req.setOuterCode("abc");
			// req.setIsMobile(true);
			// req.setVipCard("true");
			// req.setOverseasItem("false");
			//
			response = client.execute(req);
		} catch (Exception e) {
			e.printStackTrace();
			if (maxRetryTimes > 0) {
				try {
					Thread.sleep(2000L);
				} catch (InterruptedException e1) {
				}
				//
				response = getResponseByHttp(cid, pageNo, maxRetryTimes - 1);
			}
		}
		return response;
	}

	/**
	 * @param args
	 * @throws ApiException
	 */
	public static void main(String[] args) throws ApiException {
		YmmTbkItemCatcher ymmItemCatcher = new YmmTbkItemCatcher();
		ymmItemCatcher.catchAllItems();
	}
}

/**
 * 参数说明：
 * 1. fields Field List 必须 num_iid
 * 需返回的字段列表.可选值:num_iid,title,nick,pic_url,price,click_url,commission,commission_rate,commission_num
 * ,commission_volume,shop_click_url,seller_credit_score,item_location,volume ;字段之间用","分隔
 * 
 * 2. nick String 可选 jay
 * 淘宝用户昵称，注：指的是淘宝的会员登录名.如果昵称错误,那么客户就收不到佣金.每个淘宝昵称都对应于一个pid，在这里输入要结算佣金的淘宝昵称，当推广的商品成功后，佣金会打入此输入的淘宝昵称的账户。
 * 具体的信息可以登入阿里妈妈的网站查看. 注意nick和pid至少需要传递一个,如果2个都传了,将以pid为准
 * 
 * 3. pid Number 可选 123456 用户的pid,必须是mm_xxxx_0_0这种格式中间的"xxxx".
 * 注意nick和pid至少需要传递一个,如果2个都传了,将以pid为准,且pid的最大长度是20。第一次调用接口的用户，推荐该入参不要填写，使用nick=（淘宝账号）的方式去获取，以免出错。
 * 
 * 4. keyword String 特殊可选 abc 商品标题中包含的关键字. 注意:查询时keyword,cid至少选择其中一个参数
 * 
 * 5. cid Number 特殊可选 123 标准商品后台类目id。该ID可以通过taobao.itemcats.get接口获取到。
 * 
 * 6. start_price String 可选 1 起始价格.传入价格参数时,需注意起始价格和最高价格必须一起传入,并且 start_price <= end_price
 * 
 * 7. end_price String 可选 999 最高价格
 * 
 * 8. auto_send String 可选 true 是否自动发货
 * 
 * 9. area String 可选 杭州 商品所在地
 * 
 * 10.start_credit String 可选 1heart 卖家信用: 1heart(一心) 2heart (两心) 3heart(三心) 4heart(四心) 5heart(五心) 1diamond(一钻)
 * 2diamond(两钻) 3diamond(三钻) 4diamond(四钻) 5diamond(五钻) 1crown(一冠) 2crown(两冠) 3crown(三冠) 4crown(四冠) 5crown(五冠)
 * 1goldencrown(一黄冠) 2goldencrown(二黄冠) 3goldencrown(三黄冠) 4goldencrown(四黄冠) 5goldencrown(五黄冠)
 * 
 * 11.end_credit String 可选 1heart 可选值和start_credit一样.start_credit的值一定要小于或等于end_credit的值。注：end_credit与start_credit一起使用才生效
 * 
 * 12.sort String 可选 price_desc 默认排序:default price_desc(价格从高到低) price_asc(价格从低到高) credit_desc(信用等级从高到低)
 * commissionRate_desc(佣金比率从高到低) commissionRate_asc(佣金比率从低到高) commissionNum_desc(成交量成高到低) commissionNum_asc(成交量从低到高)
 * commissionVolume_desc(总支出佣金从高到低) commissionVolume_asc(总支出佣金从低到高) delistTime_desc(商品下架时间从高到低)
 * delistTime_asc(商品下架时间从低到高)
 * 
 * 13.guarantee String 可选 true 是否查询消保卖家
 * 
 * 14.start_commissionRate String 可选 1234 佣金比率下限，如：1234表示12.34%
 * 
 * 15.end_commissionRate String 可选 2345 佣金比率上限，如：2345表示23.45%。注：start_commissionRate和end_commissionRate一起设置才有效。
 * 
 * 16.start_commissionNum String 可选 1000 30天累计推广量（与返回数据中的commission_num字段对应）下限.注：该字段要与end_commissionNum一起使用才生效
 * 
 * 17.end_commissionNum String 可选 10000 30天累计推广量（与返回数据中的commission_num字段对应）上限.
 * 
 * 18.start_totalnum String 可选 1 商品总成交量（与返回字段volume对应）下限。
 * 
 * 19.end_totalnum String 可选 10 商品总成交量（与返回字段volume对应）上限。
 * 
 * 20.cash_coupon String 可选 true 是否支持抵价券，设置为true表示该商品支持抵价券，设置为false或不设置表示不判断这个属性
 * 
 * 21.vip_card String 可选 true 是否支持VIP卡，设置为true表示该商品支持VIP卡，设置为false或不设置表示不判断这个属性
 * 
 * 22.overseas_item String 可选 true false 是否海外商品，设置为true表示该商品是属于海外商品，默认为false
 * 
 * 23.sevendays_return String 可选 true 是否支持7天退换，设置为true表示该商品支持7天退换，设置为false或不设置表示不判断这个属性
 * 
 * 24.real_describe String 可选 true 是否如实描述(即:先行赔付)商品，设置为true表示该商品是如实描述的商品，设置为false或不设置表示不判断这个属性
 * 
 * 25.onemonth_repair String 可选 true 是否30天维修，设置为true表示该商品是支持30天维修，设置为false或不设置表示不判断这个属性
 * 
 * 26.cash_ondelivery String 可选 true 是否支持货到付款，设置为true表示该商品是支持货到付款，设置为false或不设置表示不判断这个属性
 * 
 * 27.mall_item String 可选 true 是否商城的商品，设置为true表示该商品是属于淘宝商城的商品，设置为false或不设置表示不判断这个属性
 * 
 * 28.page_no Number 可选 1 结果页数.1~10
 * 
 * 29.page_size Number 可选 40 40 每页返回结果数.最大每页40
 * 
 * 30.outer_code String 可选 abc 自定义输入串.格式:英文和数字组成;长度不能大于12个字符,区分不同的推广渠道,如:bbs,表示bbs为推广渠道;blog,表示blog为推广渠道.
 * 
 * 31.is_mobile Boolean 可选 true false 标识一个应用是否来在无线或者手机应用,如果是true则会使用其他规则加密点击串.如果不传值,则默认是false.
 * 
 * 32.refer_type Number 可选 1 1 点击串跳转类型，1：单品，2：单品中间页（无线暂无）
 */

