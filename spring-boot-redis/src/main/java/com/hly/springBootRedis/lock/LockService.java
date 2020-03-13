package com.hly.springBootRedis.lock;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/3/8
 * @QQ :1136513099
 * @desc :
 */
public class LockService {

    private static JedisPool pool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数
        config.setMaxTotal(200);
        //最大空闲数
        config.setMaxIdle(50);
        //最大等待时间
        config.setMaxWaitMillis(1000 * 100);
        //是否需要验证
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, "106.13.9.117", 6379, 5000);
    }

    private RedisLock lock = new RedisLock(pool);

    public void test() {

        String val = lock.getLock(5000L, 5000L);
        if (val == null) {
            System.out.println(Thread.currentThread().getName() + "获取锁失败!");
            return;
        }
        System.out.println(Thread.currentThread().getName() + "成功获取锁：" + val);
        lock.unLock(val);
    }
}
