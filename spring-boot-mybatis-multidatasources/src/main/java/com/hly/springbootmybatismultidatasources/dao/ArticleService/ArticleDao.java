package com.hly.springbootmybatismultidatasources.dao.articleService;

import com.hly.springbootmybatismultidatasources.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2019/4/9
 */
@Repository
public interface ArticleDao {

    List<Article> getArticles();

}
