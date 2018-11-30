package com.hly.springbootSecurity.service.impl;

import com.hly.springbootSecurity.domain.Article;
import com.hly.springbootSecurity.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private List<Article> list = new ArrayList<>();

    public ArticleServiceImpl() {
        list.add(new Article(1,"java","java从入门到搬砖"));
        list.add(new Article(2,"SQL","SQL从删库到跑路"));
        list.add(new Article(3,"SQL","SQL从删库到跑路"));
        list.add(new Article(4,"SQL","SQL从删库到跑路"));
        list.add(new Article(5,"SQL","SQL从删库到跑路"));
        list.add(new Article(6,"SQL","SQL从删库到跑路"));
        list.add(new Article(7,"SQL","SQL从删库到跑路"));
        //System.err.println(list);
    }

    @Override
    public List<Article> getArticles() {
        return list;
    }

    @Override
    public void deleteArticle(int id) {
        Iterator iter = list.iterator();
        while(iter.hasNext()){
            Article article = (Article)iter.next();
            if(article.getId()==id){
                iter.remove();
            }
        }
    }
}
