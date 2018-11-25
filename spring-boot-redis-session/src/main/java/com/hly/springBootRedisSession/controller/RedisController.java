package com.hly.springBootRedisSession.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :blog.csdn.net/Sirius_hly
 * @date :2018/11/25
 */
@RestController
public class RedisController {
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/redis")
    public String redisTest(){
        String key = "hly";
        //缓存Key
        stringRedisTemplate.opsForValue().set(key,"123");
        //stringRedisTemplate.opsForValue().set(key,"123",60*10, TimeUnit.SECONDS);
        //判断缓存是否存在
        System.err.println(stringRedisTemplate.hasKey(key));
        return stringRedisTemplate.opsForValue().get(key);
    }

    /*@RequestMapping("/uuid")
    public String sessionTest(HttpSession session){
        UUID uuid = (UUID)session.getAttribute("uuid");
        if(uuid == null){
            uuid = UUID.randomUUID();
        }
        session.setAttribute("uuid",uuid);
        return session.getId();
    }*/




}
