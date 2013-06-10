package com.tbk.ymm.utils;

import com.tbk.ymm.commons.model.article.YmmArticle;

/**
 * @author David (qidawei@xiaomi.com)
 */
public class ArticleUtil {

	/**
	 * 将空格替换成 &nbsp; 
	 * 
	 * @param article
	 */
	public static void replaceWithHtmlSymbol(YmmArticle article) {
		if (null != article.getContent()) {
			String content = article.getContent();
			content.replaceAll(" ", "&nbsp;");
			article.setContent(content);
		}
		if (null != article.getBrief()) {
			String brief = article.getBrief();
			brief.replaceAll(" ", "&nbsp;");
			article.setBrief(brief);
		}
	}
}
