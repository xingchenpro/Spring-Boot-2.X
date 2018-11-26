package com.hly.springBootRedis.controller;

import com.hly.springBootRedis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :blog.csdn.net/Sirius_hly
 * @date :2018/11/26
 */
@RestController
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("redis/addUser")
    public User addUser(){
        User  user = new User();
        user.setUsername("java");
        user.setPassword("123");
        ValueOperations<String,User> operations = redisTemplate.opsForValue();
        operations.set("user",user);
        //operations.set("user",user,1, TimeUnit.SECONDS);
        return user;
    }
}
