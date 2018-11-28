package com.hly.springBootSecurityMybatis.service;


import com.hly.springBootSecurityMybatis.entity.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getArticles();
    void deleteArticle(int id);
}
