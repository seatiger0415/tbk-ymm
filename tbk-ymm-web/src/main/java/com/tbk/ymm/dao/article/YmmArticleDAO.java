package com.tbk.ymm.dao.article;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.tbk.ymm.commons.model.article.YmmArticle;

/**
 * @author David (qidawei@xiaomi.com)
 */
@DAO
public interface YmmArticleDAO {

	public static final String FIELDS = "id, article_cate_id, article_series_id, " +
			"title, content, brief, status, create_time, update_time";
	public static final String UPDATE_FIELDS = "article_cate_id, article_series_id, " +
			"title, content, brief, status, create_time, update_time";

	@SQL("SELECT " + FIELDS + " FROM " + YmmArticle.TABLE + " WHERE id = :1")
	public YmmArticle getByIdAdmin(int id);

	@SQL("SELECT " + FIELDS + " FROM " + YmmArticle.TABLE + " WHERE id = :1 AND status = "
			+ YmmArticle.STATUS_NORMAL)
	public YmmArticle getById(int id);

	@SQL("SELECT " + FIELDS + " FROM " + YmmArticle.TABLE + " WHERE article_cate_id = :1")
	public List<YmmArticle> getListByCidAdmin(int articleCateId);

	@SQL("SELECT " + FIELDS + " FROM " + YmmArticle.TABLE + " WHERE article_cate_id = :1 AND status = "
			+ YmmArticle.STATUS_NORMAL)
	public List<YmmArticle> getListByCid(int articleCateId);

	// ------------------------------------------------------

	@SQL("INSERT INTO " + YmmArticle.TABLE + "(" + FIELDS + ") VALUES (:1.id, :1.articleCateId, " +
			":1.articleSeriesId, :1.title, :1.content, :1.brief, :1.status, :1.createTime, " +
			":1.updateTime) ON DUPLICATE KEY UPDATE article_cate_id = VALUES(article_cate_id), " +
			"article_series_id = VALUES(article_series_id), title = VALUES(title), " +
			"content = VALUES(content), brief = VALUES(brief), status = VALUES(status), " +
			"update_time = VALUES(update_time)")
	public void insertOrUpdate(YmmArticle article);

	@SQL("INSERT INTO " + YmmArticle.TABLE + "(" + UPDATE_FIELDS + ") VALUES (:1.articleCateId, " +
			":1.articleSeriesId, :1.title, :1.content, :1.brief, :1.status, :1.createTime, " +
			":1.updateTime)")
	public void insert(YmmArticle article);

	@SQL("UPDATE " + YmmArticle.TABLE + " SET status = " + YmmArticle.STATUS_DELETE + " WHERE id = :1")
	public void remove(int id);

	// public void insertOrUpdateList(List<YmmArticle> articleList);

}
