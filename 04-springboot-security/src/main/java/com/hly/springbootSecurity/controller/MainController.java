package com.hly.springbootSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :blog.csdn.net/Sirius_hly
 * @date :2018/10/29
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String root(){
        return "redirect:/index";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("article/article")
    public String article(){
        return "article/article";
    }

    //@RequestMapping将接收Get,Post,Head,Options等所有的请求方式
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(ModelAndView modelAndView){
        modelAndView.addObject("loginError",true);
        return "login";
    }

    //@RequestMapping(method = RequestMethod.GET)的缩写
    @GetMapping("401")
    public String error(){
        return "401";
    }





}
