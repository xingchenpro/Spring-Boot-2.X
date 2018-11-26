package com.hly.springBootRedis.controller;

import com.hly.springBootRedis.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :blog.csdn.net/Sirius_hly
 * @date :2018/11/26
 */
@RestController
public class CacheController {

    /**
     * value的值就是缓存到redis中的key
     * key 使用SpEL表达式自定义的缓存Key，比如：#username是以参数username为key的缓存，##user.username是以返回结果的username作为key的缓存；
     * @return
     */

    //把集合写入redis，放进缓存
    @GetMapping("/cache/addUsers")
    @Cacheable(value = "user")//读取数据到方法上，先从缓存中读取，没有再从数据库中读取
    public List<User> postAllUser() {
        User user1 = new User("hly","124");
        User user2 = new User("sirius","123");
        List<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        return users;
    }


    //空方法，拿上面方法的缓存
    @GetMapping("/cache/all")
    @Cacheable(value = "user")//读取数据到方法上，先从缓存中读取，没有再从数据库中读取
    public List<User> getAllUser() {
        List<User> users = new ArrayList<User>();
        return users;
    }

    //增加，修改
    @CachePut(value = "user")
    @GetMapping("/cache/update")
    public List<User> updateUsers() {
        List<User> users = new ArrayList<User>();
        User user1 = new User("hly","125");
        users.add(user1);
        return users;
    }

    //删除空方法设置的缓存
    //http://localhost:8080/cache/del
    @GetMapping("/cache/del")
    @CacheEvict(value = "user")
    public String delAllCache() {
        //删除后redis中还有
        return "以删除所有缓存";
    }


    //EL表达式来指定的key，有则取出，无则放入缓存,返回到方法User返回参数，没有则设置User user
    //http://localhost:8080/cache/object?username=hly
    @Cacheable(value = "user",key="#user.username")
    @GetMapping("/cache/object")
    public User getUser(User user) {
        user.setUsername("hly");
        user.setPassword("129");
        return user;
    }

    //根据键值，增加，修改
    @CachePut(value = "user",key="#user.username")
    @GetMapping("/cache/update/object")
    public User updateUser(User user) {
        user.setUsername("hly");
        user.setPassword("128");
        return user;
    }

    //按名字删除缓存
    //http://localhost:8080/cache/del/object?username=hly
    @GetMapping("/cache/del/object")
    @CacheEvict(value = "user",key="#user.username")
    public String delCacheByName(User user) {
        //删除后redis中还有
        return "按名字删除缓存";
    }
}
