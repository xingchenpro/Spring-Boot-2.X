package com.hly.springBootRedisSession.controller;

import com.hly.springBootRedisSession.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :blog.csdn.net/Sirius_hly
 * @date :2018/11/25
 */
@Controller
public class UserController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/")
    public ModelAndView login_page() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    /*@RequestMapping("/login")
    public String login(@RequestParam("username") String username,@RequestParam("password") String password){
        if(stringRedisTemplate.opsForValue().get(username).equals(password))
            return "redirect:/admin";
        return "redirect:/";
    }*/

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        if (session.getAttribute("uuid") != null)
            return "redirect:/admin";
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    @ResponseBody
    @RequestMapping("/getUserName")
    public Result getUserName(HttpServletRequest request) {
        Result result = new Result();
        String username = (String) request.getSession().getAttribute("username");
        System.err.println(request.getSession());
        System.err.println(username);
        result.setResult(username);
        return result;
    }
}
