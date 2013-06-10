package com.tbk.ymm.commons.model.article;

import java.util.Date;

/**
 * 文章模型
 * 
 * @author David (qidawei@xiaomi.com)
 */
public class YmmArticle {

	public static final String TABLE = "ymm_article";
	public static final int STATUS_NORMAL = 1;
	public static final int STATUS_DELETE = -1;

	private int id;
	private int articleCateId;
	private int articleSeriesId = 0; // 文章属于哪一个系列
	private String title;
	private String content;
	private String brief;
	private int status = STATUS_NORMAL;

	private Date createTime;
	private Date updateTime;

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

}
