package com.hly.springbootmybatismultidatasources.controller;

import com.hly.springbootmybatismultidatasources.dao.articleService.ArticleDao;
import com.hly.springbootmybatismultidatasources.dao.videoService.VideoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2019/4/9
 */
@RestController
public class TestController {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    VideoDao videoDao;

    @RequestMapping(value = "/articles")
    public Object getArticle(){
        return articleDao.getArticles();
    }

    @RequestMapping(value = "/videos")
    public Object getVideoByArticleId(int a_id){
        return videoDao.getVideoByArticleId(a_id);
    }

}
