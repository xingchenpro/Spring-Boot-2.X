package com.hly.springBootRedis.controller;

import com.hly.springBootRedis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    //只针对键值对都是字符型的数据进行操作
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("redis/addUser")
    public User addUserByRedis(){
        //保存对象
        User  user = new User();
        user.setUsername("hly");
        user.setPassword("123");
        ValueOperations<String,User> operations = redisTemplate.opsForValue();
        operations.set("user",user);
        System.err.println(operations.get("user"));

        ValueOperations operations1 = stringRedisTemplate.opsForValue();
        operations1.set("hly","123");
        System.err.println(operations1.get("hly"));
        //operations.set("user",user,1, TimeUnit.SECONDS);
        return user;
    }
}
