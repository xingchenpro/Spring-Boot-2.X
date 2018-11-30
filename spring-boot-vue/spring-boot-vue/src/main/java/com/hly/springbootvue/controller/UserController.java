package com.hly.springbootvue.controller;

import com.hly.springbootvue.bean.ResultBean;
import com.hly.springbootvue.bean.User;
import com.hly.springbootvue.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultBean login(@RequestParam(value="username",required=false)String username,@RequestParam(value="password",required=false)String password , HttpServletResponse response) throws IOException {
        System.err.println(username+": "+password);
        if(password.equals(userDao.selectUserByUsername(username).getPassword())) {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("{\"status\":\"success\",\"msg\":\"登录成功\"}");
            out.flush();
            out.close();

        }else {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("{\"status\":\"error\",\"msg\":\"登录失败\"}");
            out.flush();
            out.close();
        }
        return  null;
    }

}
