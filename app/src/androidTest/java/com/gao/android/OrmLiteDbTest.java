package com.gao.android;

import android.test.AndroidTestCase;

import com.gao.android.db.Article;
import com.gao.android.db.ArticleDao;
import com.gao.android.db.User;
import com.gao.android.db.UserDao;
import com.orhanobut.logger.Logger;

import java.util.Collection;
import java.util.List;

/**
 * Created by GaoMatrix on 2016/9/12.
 */
public class OrmLiteDbTest extends AndroidTestCase {
    public void testAddArticle() {
        User user = new User();
        user.setName("li");
        new UserDao(getContext()).add(user);
        Article article = new Article();
        article.setTitle("dhfidhf的使用");
        article.setUser(user);
        new ArticleDao(getContext()).add(article);
    }

    public void testGetArticleWithUser() {
        Article article = new ArticleDao(getContext()).getArticleWithUser(1);
        Logger.d(article.toString());
    }

    public void testGetArticleById(int id) {
        Article article = new ArticleDao(getContext()).get(1);
        Logger.d(article.toString());
    }

    public void testListArticlesByUserId() {
        List<Article> articles = new ArticleDao(getContext()).listByUserid(1);
        Logger.d(articles);
    }

    public void testGetUserById() {
        User user = new UserDao(getContext()).get(1);
        Logger.d(user.toString());
        Collection<Article> articles = user.getArticles();
        if (articles != null) {
            for (Article article : articles) {
                Logger.d(article.toString());
            }
        }
    }

    public void testWhere() {
    }
}
