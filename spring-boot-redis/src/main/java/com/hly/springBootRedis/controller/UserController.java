package com.hly.springBootRedis.controller;

import com.hly.springBootRedis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :blog.csdn.net/Sirius_hly
 * @date :2018/11/25
 */
@Controller
public class UserController {

    //只针对键值对都是字符型的数据进行操作
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/")
    public ModelAndView login_page(){
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.setViewName("/login");
       return modelAndView;
    }

    /*@RequestMapping("/login")
    public String login(@RequestParam("username") String username,@RequestParam("password") String password){
        if(redisTemplate.opsForValue().get(username).equals(password))
            return "redirect:/admin";
        return "redirect:/";
    }*/

    @ResponseBody
    @RequestMapping("/uuid")
    public String sessionTest(HttpSession session){
        UUID uuid = (UUID)session.getAttribute("uuid");
        if(uuid == null){
            uuid = UUID.randomUUID();
        }
        session.setAttribute("uuid",uuid);
        return session.getId();
    }

    //往 Redis 添加Session
    @ResponseBody
    @RequestMapping("/setUserName")
    public Result sessionTest(HttpServletRequest request){
        Result result = new Result();
        String username = (String) request.getSession().getAttribute("username");
        if(username==null){
            username = "123";
        }
        request.getSession().setAttribute("username",username);
        result.setResult(request.getSession().getAttribute("username"));
        return result;
    }


    //分布式Session
    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
       if(session.getAttribute("uuid")!=null)
            return "redirect:/admin";
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }
}
