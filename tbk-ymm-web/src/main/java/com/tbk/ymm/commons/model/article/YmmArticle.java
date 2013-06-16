package com.tbk.ymm.commons.model.article;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

/**
 * 文章模型
 * 
 * @author David (qidawei@xiaomi.com)
 */
public class YmmArticle {

	private static final int SIMPLE_TITLE_LEN = 15;
	public static final String TABLE = "ymm_article";
	public static final int STATUS_NORMAL = 1;
	public static final int STATUS_DELETE = -1;

	private int id;
	private int articleCateId;
	private int articleSeriesId = 0; // 文章属于哪一个系列
	private String title;
	private String content;
	private String brief;
	private String keywords; // 关键字列表，用半角逗号分隔
	private int status = STATUS_NORMAL;
	private int rank = 0;

	private Date createTime;
	private Date updateTime;

	/**
	 * 带截断的文章标题
	 * 
	 * @return
	 */
	public String getSimpleTitle() {
		if (StringUtils.isEmpty(title)) {
			return "";
		}
		if (title.length() <= SIMPLE_TITLE_LEN) {
			return title;
		}
		return title.substring(0, SIMPLE_TITLE_LEN);
	}

	public List<String> keywordsList() {
		if (StringUtils.isEmpty(keywords)) {
			return Collections.emptyList();
		}
		String[] keywordArr = keywords.split(",");
		List<String> kList = Lists.newArrayList();
		for (String keyword : keywordArr) {
			if (null == keyword) {
				continue;
			}
			String k = keyword.trim();
			if (!StringUtils.isEmpty(k)) {
				kList.add(k);
			}
		}
		return kList;
	}

	// --------------------------------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArticleCateId() {
		return articleCateId;
	}

	public void setArticleCateId(int articleCateId) {
		this.articleCateId = articleCateId;
	}

	public int getArticleSeriesId() {
		return articleSeriesId;
	}

	public void setArticleSeriesId(int articleSeriesId) {
		this.articleSeriesId = articleSeriesId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

}
