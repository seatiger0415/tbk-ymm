package com.tbk.ymm.commons.enums;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Maps;

/**
 * 对购物类目页来讲，就是把精品文章的题目放到关键字和title中；<br/>
 * <br/>
 * 各个购物攻略类目的固定title, keywords, descrption，需要和动态的结合使用;<br/>
 * 注意：这里配的词不包括各个类目的名字和‘名字+价格’：<br/>
 * 类目名字(导航_一级_二级)关键字，是放在title的最前面的，在配上'价格'后，一起放在keywords的最后面的
 * 
 * @author David (qidawei@xiaomi.com)
 */
public enum ArticleCatePageMetaSetter {

	Common(0, null, "孕妈妈_最实用的孕妈妈导购网站_最精美的孕妇购物攻略",
			"防辐射服价格,孕妇装价格,十月妈咪怎么样,孕妇装夏装,防辐射服哪个牌子好,孕妇内衣",
			"孕妈妈良品导购,有品质又实用的孕妈妈导购网站,为孕妈妈们提供靠谱和精致的物品推荐。"), //
	FangFuShe(3, Common, "防辐射服哪个牌子好_防辐射孕妇装_防辐射衣服",
			"如何购买防辐射服,消费者最喜爱的防辐射服品牌,孕妇装十大品牌排名,现在市面上的防辐射服哪个牌子好",
			"权威的防辐射服排名,权威的防辐射服品牌推荐,来看孕妈妈精心挑选出的最精美的防辐射服的购买心得,购买链接"), //
	NeiYi(4, Common, "孕妇内衣哪个牌子好_如何选择孕妇内衣_孕妇内衣选购攻略",
			"孕妇如何选择内衣,孕妇内衣选购全攻略,孕妇文胸品牌,孕妈咪该如何选内衣",
			"怀孕期及分娩后的女人,要格外精心为自己选购款式、功能合适的内衣,来看孕妈妈精心挑选出的最精美的孕妇内衣的购买心得,购买链接"), //
	YunFuZhuang(5, Common, "孕妇装哪个牌子好_孕妇装排名_孕妇装品牌",
			"孕妇托腹裤选购攻略,夏天如何选购孕妇装注意事项,孕妇装购买攻略,孕妇装夏装,孕妇装品牌，孕妇装排名",
			"权威的防孕妇装排名,权威的孕妇装服品牌推荐,来看孕妈妈精心挑选出的最精美的孕妇装的购买心得,购买链接"), //
	Xihu(6, Common, "孕妇洗发水_孕妇沐浴露_孕妇洗护用品攻略",
			"准妈妈护肤攻略,孕妇护肤品十大品牌排行榜,孕妇沐浴露,孕妇洗护用品推荐",
			"怀孕妈妈的洗护用品需要精心挑选,孕妇乳头更要小心保护,来看孕妈妈精心挑选出的最精美的孕妇洗护用品的购买心得,购买链接"), //
	YingYangPin(7, Common, "孕妇DHA_孕妇吃什么营养品好_孕营养品_孕妇补品",
			"孕妇DHA,孕妇吃什么营养品好,孕营养品,孕期补充营养素",
			"怀孕妈妈需要更多营养,一日三餐之余,需要额外补充各种维生素、钙、锌等营养元素,来看孕妈妈精心挑选出的最精美的孕妇营养品的购买心得,购买链接"), //
	QiTa(8, Common, "常用孕妇用品_孕妇必备_孕妇一次性用品_孕妇收腹带_一次性防溢乳垫",
			"手动吸奶器,吸乳器,挤奶器,孕妇待产包,孕妇用品,母乳存储杯,一次性内裤",
			"怀孕后,孕妈妈需要准备各种物品,来看孕妈妈精心挑选出的最精美的孕妇常用物品的购买心得,购买链接"), //
	// 二级类目也要写
	;

	private static Map<Long, ArticleCatePageMetaSetter> map = Maps.newHashMap();
	static {
		for (ArticleCatePageMetaSetter pageMetaSetter : ArticleCatePageMetaSetter.values()) {
			map.put(pageMetaSetter.cateId, pageMetaSetter);
		}
	}

	public String getTitle() {
		if (StringUtils.isEmpty(this.title)) {
			return this.parent.title;
		}
		return this.title;
	}

	public String getKeywords() {
		if (StringUtils.isEmpty(this.keywords)) {
			return this.parent.keywords;
		}
		return this.keywords;
	}

	public String getDescrption() {
		if (StringUtils.isEmpty(this.descrption)) {
			return this.parent.descrption;
		}
		return this.descrption;
	}

	private long cateId;
	private ArticleCatePageMetaSetter parent;
	private String title;
	private String keywords;
	private String descrption;

	private ArticleCatePageMetaSetter(long cateId, ArticleCatePageMetaSetter parent, String title, String keywords,
			String descrption) {
		this.cateId = cateId;
		this.parent = parent;
		this.title = title;
		this.keywords = keywords;
		this.descrption = descrption;
	}

	public long getCateId() {
		return cateId;
	}

	public static ArticleCatePageMetaSetter getEnumByCateId(long cateId) {
		return map.get(cateId);
	}

}
