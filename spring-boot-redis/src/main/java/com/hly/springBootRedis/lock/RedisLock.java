package com.hly.springBootRedis.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/3/8
 * @QQ :1136513099
 * @desc :Redis 实现分布式锁
 */
public class RedisLock {

    private JedisPool jedisPool;

    private String redisKey = "redisKey";

    public RedisLock(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public String getLock(Long acquireTime, Long timeOut) {
        Jedis con = null;
        try {
            //建立链接
            con = jedisPool.getResource();
            //定义key值
            String value = UUID.randomUUID().toString();
            //获取锁之后的超时时间
            int expire = (int) (timeOut / 1000);
            Long endTime = System.currentTimeMillis() + acquireTime;
            while (System.currentTimeMillis() < endTime) {
                //获取锁
                if (con.setnx(redisKey, value) == 1) {
                    //设置超时时间防止死锁
                    con.expire(redisKey, expire);
                    return value;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public void unLock(String val) {
        Jedis con = null;
        //自己删除自己创建的锁
        con = jedisPool.getResource();
        try {
            if (val.equals(con.get(redisKey))) {
                con.del(redisKey);
                System.out.println(Thread.currentThread().getName()+"释放锁成功>>>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
}
