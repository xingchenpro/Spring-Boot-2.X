package com.hly.springbootSecurity.service;

import com.hly.springbootSecurity.domain.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getArticles();
    void deleteArticle(int id);
}
