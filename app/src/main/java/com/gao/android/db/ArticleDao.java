package com.gao.android.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by GaoMatrix on 2016/9/12.
 */
public class ArticleDao {
    private Dao<Article, Integer> mArticleDaoOpe;
    private DatabaseHelper mDatabaseHelper;

    @SuppressWarnings("unchecked")
    public ArticleDao(Context context) {
        mDatabaseHelper = DatabaseHelper.getHelper(context);
        try {
            mArticleDaoOpe = mDatabaseHelper.getDao(Article.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Article article) {
        try {
            mArticleDaoOpe.create(article);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过Id得到一篇文章
     *
     * @param id
     * @return
     */
    public Article get(int id) {
        Article article = null;
        try {
            article = mArticleDaoOpe.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    /**
     * 通过Id得到一个Article
     *
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public Article getArticleWithUser(int id) {
        Article article = null;
        try {
            article = mArticleDaoOpe.queryForId(id);
            mDatabaseHelper.getDao(User.class).refresh(article.getUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    public List<Article> listByUserid(int userId) {
        try {
            return mArticleDaoOpe.queryBuilder().where().eq("user_id", userId).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void queryByWhere() {
        try {
            mArticleDaoOpe.queryBuilder().where().eq("user_id", 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
