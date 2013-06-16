package com.tbk.ymm.utils;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tbk.ymm.commons.consts.YmmConsts;
import com.tbk.ymm.commons.dto.PageMeta;
import com.tbk.ymm.commons.dto.YmmCateBarDTO;
import com.tbk.ymm.commons.enums.ArticleCatePageMetaSetter;
import com.tbk.ymm.commons.enums.CatePageMetaSetter;
import com.tbk.ymm.commons.model.YmmNavigationCate;
import com.tbk.ymm.commons.model.article.YmmArticle;
import com.tbk.ymm.commons.model.article.YmmArticleCate;
import com.tbk.ymm.data.catcher.commons.model.YmmItemCate;

/**
 * @author David (qidawei@xiaomi.com)
 */
public class PageMetaUtil {

	/**
	 * 分类页面的title, keywords, description；<br/>
	 * 目前的策略是加入依次加入 导航、一级、二级类目，然后通过CatePageMetaSetter混入每个类目指定的关键词
	 * 
	 * @param ymmCateBarDTO
	 * @return
	 */
	public static PageMeta getCurMetaForCatePage(YmmCateBarDTO ymmCateBarDTO) {
		PageMeta pageMeta = new PageMeta();
		StringBuilder sbTitle = new StringBuilder();
		StringBuilder sbKeywords = new StringBuilder();
		StringBuilder sbDescrption = new StringBuilder();
		//
		// 1. 导航
		YmmNavigationCate curNavCate = ymmCateBarDTO.getCurNavCate();
		if (null != curNavCate && !StringUtils.isEmpty(curNavCate.getName())) {
			sbTitle.append(curNavCate.getName()).append(YmmConsts.TITLE_DELIMETER);
			//
			CatePageMetaSetter pageMetaSetter = CatePageMetaSetter.getEnumByCateId(curNavCate.getId());
			if (null != pageMetaSetter) {
				sbKeywords.append(pageMetaSetter.getKeywords()).append(YmmConsts.KEYWORDS_DELIMETER);
				sbDescrption.append(pageMetaSetter.getDescrption());
			}
			sbKeywords.append(curNavCate.getName()).append(YmmConsts.KEYWORDS_DELIMETER)
					.append(curNavCate.getName()).append("价格").append(YmmConsts.KEYWORDS_DELIMETER);
		}
		// 2. 一级类目
		YmmItemCate curLv1Cate = ymmCateBarDTO.getCurLv1Cate();
		if (null != curLv1Cate && !StringUtils.isEmpty(curLv1Cate.getName())) {
			// 以导航为准，如果当前一级类目和导航类目不是一对一，则有机会显示出一级类目
			if (null == curNavCate || !curLv1Cate.getName().equals(curNavCate.getName())) {
				String cateNameForTitle = replaceName(curLv1Cate.getName(), YmmConsts.TITLE_DELIMETER);
				String cateNameForKeywords = replaceName(curLv1Cate.getName(), YmmConsts.KEYWORDS_DELIMETER);
				//
				sbTitle.append(cateNameForTitle).append(YmmConsts.TITLE_DELIMETER);
				//
				sbKeywords.append(cateNameForKeywords).append(YmmConsts.KEYWORDS_DELIMETER)
						.append(cateNameForKeywords).append("价格").append(YmmConsts.KEYWORDS_DELIMETER);
			}
		}
		// 3. 二级类目
		YmmItemCate curLv2Cate = ymmCateBarDTO.getCurLv2Cate();
		if (null != curLv2Cate && !StringUtils.isEmpty(curLv2Cate.getName())) {
			String cateNameForTitle = replaceName(curLv2Cate.getName(), YmmConsts.TITLE_DELIMETER);
			String cateNameForKeywords = replaceName(curLv2Cate.getName(), YmmConsts.KEYWORDS_DELIMETER);
			//
			sbTitle.append(cateNameForTitle).append(YmmConsts.TITLE_DELIMETER);
			//
			sbKeywords.append(cateNameForKeywords).append(YmmConsts.KEYWORDS_DELIMETER)
					.append(cateNameForKeywords).append("价格");
		}
		//
		if (null != curNavCate) {
			CatePageMetaSetter pageMetaSetter = CatePageMetaSetter.getEnumByCateId(curNavCate.getId());
			if (null != pageMetaSetter) {
				sbTitle.append(pageMetaSetter.getTitle()).append(YmmConsts.TITLE_DELIMETER);
			}
		}
		sbTitle.append(YmmConsts.SITE_NAME);
		//
		pageMeta.setTitle(sbTitle.toString());
		pageMeta.setKeywords(sbKeywords.toString());
		pageMeta.setDescription(sbDescrption.toString());
		//
		return pageMeta;
	}

	/**
	 * 购物攻略分类页面的title, keywords, description；<br/>
	 * 目前的策略是加入攻略类目，然后通过ArticleCatePageMetaSetter混入每个类目指定的关键词
	 * 
	 * @param curArticleCate
	 * @return
	 */
	public static PageMeta getCurMetaForArticleCatePage(YmmArticleCate curArticleCate) {
		PageMeta pageMeta = new PageMeta();
		StringBuilder sbTitle = new StringBuilder();
		StringBuilder sbKeywords = new StringBuilder();
		StringBuilder sbDescrption = new StringBuilder();
		if (null == curArticleCate) {
			sbTitle.append(ArticleCatePageMetaSetter.Common.getTitle()).append(YmmConsts.TITLE_DELIMETER)
					.append(YmmConsts.SITE_NAME);
			sbKeywords.append(ArticleCatePageMetaSetter.Common.getKeywords());
			sbDescrption.append(ArticleCatePageMetaSetter.Common.getDescrption());
		} else {
			// 二级类目
			String cateNameForTitle = replaceName(curArticleCate.getName(), YmmConsts.TITLE_DELIMETER);
			String cateNameForKeywords = replaceName(curArticleCate.getName(), YmmConsts.KEYWORDS_DELIMETER);
			//
			sbTitle.append(cateNameForTitle).append(YmmConsts.TITLE_DELIMETER);
			sbKeywords.append(cateNameForKeywords);
			//
			ArticleCatePageMetaSetter pageMetaSetter = ArticleCatePageMetaSetter
					.getEnumByCateId(curArticleCate.getId());
			if (null != pageMetaSetter) {
				sbTitle.append(pageMetaSetter.getTitle()).append(YmmConsts.TITLE_DELIMETER);
				sbKeywords.append(YmmConsts.KEYWORDS_DELIMETER).append(pageMetaSetter.getKeywords());
				sbDescrption.append(pageMetaSetter.getDescrption());
			}
			sbTitle.append(YmmConsts.SITE_NAME);
		}
		//
		pageMeta.setTitle(sbTitle.toString());
		pageMeta.setKeywords(sbKeywords.toString());
		pageMeta.setDescription(sbDescrption.toString());
		//
		return pageMeta;
	}

	/**
	 * @param article
	 * @return
	 */
	public static PageMeta getCurMetaForArticlePage(YmmArticle article) {
		PageMeta pageMeta = new PageMeta();
		StringBuilder sbTitle = new StringBuilder();
		StringBuilder sbKeywords = new StringBuilder();
		StringBuilder sbDescrption = new StringBuilder();
		//
		if (null == article) {
			sbTitle.append("孕妈妈").append(YmmConsts.TITLE_DELIMETER).append("购物攻略");
			sbDescrption.append("孕妈妈").append(YmmConsts.TITLE_DELIMETER).append("购物攻略");
		} else {
			sbTitle.append("精品购物攻略").append(YmmConsts.TITLE_DELIMETER).append(article.getTitle())
					.append(YmmConsts.TITLE_DELIMETER);
			sbKeywords.append("精品购物攻略").append(YmmConsts.KEYWORDS_DELIMETER).append(article.getTitle());
			//
			List<String> keywordsList = article.keywordsList();
			if (!keywordsList.isEmpty()) {
				for (String k : keywordsList) {
					sbTitle.append(YmmConsts.TITLE_DELIMETER).append(k);
					sbKeywords.append(YmmConsts.KEYWORDS_DELIMETER).append(k);
				}
			}
		}
		//
		sbTitle.append(YmmConsts.TITLE_DELIMETER);
		sbTitle.append(YmmConsts.SITE_NAME);
		sbDescrption.append("孕妈妈为你推荐最精美的孕妇用商品,为您推荐最精美的购物攻略,祝您好孕！");
		//
		pageMeta.setTitle(sbTitle.toString());
		pageMeta.setKeywords(sbKeywords.toString());
		pageMeta.setDescription(sbDescrption.toString());
		//
		return pageMeta;
	}

	// ------------------------------------------------------------

	/**
	 * 替换掉一个特殊字符
	 * 
	 * @param name
	 * @param delimeter
	 * @return
	 */
	private static String replaceName(String name, String delimeter) {
		if (StringUtils.isEmpty(name)) {
			return "";
		}
		return name.replaceAll("/", delimeter).replaceAll("，", delimeter).replaceAll(" ", delimeter);
	}
}
