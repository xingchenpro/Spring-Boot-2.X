package com.hly.springbootSecurity.controller;

import com.hly.springbootSecurity.domain.Article;
import com.hly.springbootSecurity.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

public class ArticleController {

    @Autowired
    ArticleService articleService;
    /**
     * 查看文章列表
     * @param model
     * @return
     */
    @RequestMapping("/article")
    public ModelAndView articleList(Model model){
        List<Article> list = articleService.getArticles();
        model.addAttribute("articlesList",list);
        return new ModelAndView("article/list","articleModel",model);
    }
    /**
     * 给方法设置权限,没有ADMIN权限的用户不能删除文章
     * @param id
     * @param model
     * @return
     */
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/article/{id}/deletion")
    public ModelAndView delete(@PathVariable("id")int id,Model model){
        articleService.deleteArticle(id);
        model.addAttribute("articlesList",articleService.getArticles());
        return new ModelAndView("article/list","articleModel",model);
    }
    @RequestMapping("/articleDetail")
    public String article(){
        return "article/articleDetail";
    }
}
