package com.javahly.springbootelasticsearch.web;

import com.javahly.springbootelasticsearch.dao.UserDao;
import com.javahly.springbootelasticsearch.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/3/12
 * @QQ :1136513099
 * @desc :
 */
@RestController
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userDao.save(user);
    }

    @RequestMapping("/getUser")
    public Optional<User> getUser(String id) {
        return userDao.findById(id);
    }
}
