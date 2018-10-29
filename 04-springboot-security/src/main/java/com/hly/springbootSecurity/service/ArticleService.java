package com.hly.springbootSecurity.service;

import com.hly.springbootSecurity.domain.Article;

import java.util.List;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :blog.csdn.net/Sirius_hly
 * @date :2018/10/29
 */
public interface ArticleService {
    List<Article> getArticles();
    void deleteArticle(int id);
}
