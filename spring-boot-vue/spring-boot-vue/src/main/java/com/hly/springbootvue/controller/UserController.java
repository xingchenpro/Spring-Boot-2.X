package com.hly.springbootvue.controller;

import com.hly.springbootvue.bean.ResultBean;
import com.hly.springbootvue.bean.User;
import com.hly.springbootvue.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :blog.csdn.net/Sirius_hly
 * @date :2018/11/24
 */
@Controller
public class UserController {


    @Autowired
    UserDao userDao;

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultBean login(@RequestParam(value="username",required=false)String username,@RequestParam(value="password",required=false)String password){
        System.err.println(username);
        if(password.equals(userDao.selectUserByUsername(username).getPassword()))
            return  new ResultBean("success", "登录成功!");
        return  new ResultBean("error", "登录失败!");
    }

    @RequestMapping("/")
    public String index(){
        return "/index";
    }
}
