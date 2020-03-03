package com.javahly.redis.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.params.SetParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2019/6/15
 * @QQ :1136513099
 * @desc :
 */
public class RedisTest {
    private Jedis jedis;

    @Before
    public void init(){
        //创建一个jedis对象，传入服务器ip地址
        jedis = new Jedis("123.56.219.53",6379);
        System.out.println("connect successfully");
        //如果设置了密码
        //jedis.auth("123456");
        System.out.println("Server is running: "+jedis.ping());
    }

    @Test
    public void testList(){
        System.out.println(jedis.lpush("lpush", "v1","v2","v3","v4"));
        System.out.println(jedis.rpush("rpush", "v1","v2","v3","v4"));
       /* System.out.println(jedis.expire("lpush", 60));
        System.out.println(jedis.type("lpush"));
        System.out.println(jedis.ttl("lpush"));*/
        //jedis.lrange是按范围取出，第一个是key，第二个是起始位置，第三个是结束位置，jedis.lrange获取长度 -1表示取得所有
        List<String> list1 = jedis.lrange("lpush",0,5);
        List<String> list2 = jedis.lrange("rpush",0,5);
        list1.forEach(s -> System.out.print(s+" "));
        System.out.println();
        list2.forEach(s -> System.out.print(s+" "));
    }

    @Test
    public void testInc(){
        System.out.println(jedis.incr("count"));
        System.out.println(jedis.incr("count"));
        System.out.println(jedis.incr("count"));
        System.out.println(jedis.decr("count"));
        System.out.println(jedis.decr("count"));
    }

    @Test
    public void testString(){
        System.out.println(jedis.set("k2", "v2"));
        System.out.println(jedis.get("k2"));
        System.out.println(jedis.del("k2"));
        System.out.println(jedis.append("k2", "v2"));
        System.out.println(jedis.append("k2", "v2"));
        System.out.println(jedis.get("k2"));
        System.out.println(jedis.strlen("k2"));
        //一次添加多对键值对，
        System.out.println(jedis.mset("k2", "v2", "k3", "v3", "k4", "v4"));
        System.out.println(jedis.mget("k2", "k3"));
    }



    @Test
    public void testSet(){
        jedis.sadd("set1","v1","v2","v3","v4","v5","v6","v7");
        System.out.println(jedis.smembers("set1"));
        System.out.println(jedis.sismember("set1","v1"));

        jedis.sadd("set2","v1","v2","v3","v4","v11","v12");
        System.out.println(jedis.sdiff("set1", "set2")); //在第一个set里面而不在后面任何一个set里面的项(差集)
        System.out.println(jedis.sinter("set1", "set2"));//在第一个set和第二个set中都有的 （交集）
        System.out.println(jedis.sunion ("set1", "set2"));//两个集合所有元素（并集）
    }

    @Test
    public void testZset(){
        Map<String,Double> map=new HashMap();
        map.put("k1",10d);
        map.put("k4",40d);
        map.put("k3",30d);
        map.put("k2",20d);
        System.out.println(jedis.zadd("zset", map));
        System.out.println(jedis.zrange("zset", 0, -1));
        System.out.println(jedis.zrangeWithScores("zset", 0, -1));
    }

    @Test
    public void testHash(){
        Map<String,String> map= new HashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
        map.put("k3","v3");
        System.out.println(jedis.hset("hash", map));
        System.out.println(jedis.hkeys("hash"));
        System.out.println(jedis.hvals("hash"));
        System.out.println(jedis.hgetAll("hash"));
        System.out.println(jedis.hget("hash", "k1"));
    }

    @Test
    public void testLock(){
        //nx:key如果存在，就不会被设置进去,分布式锁
        System.out.println(jedis.set("k5", "v5"));
        System.out.println(jedis.setnx("k5", "kkkkk"));
        System.out.println(jedis.get("k5"));
        //ex :设置过期时间，防止死锁
        System.out.println(jedis.setex("k5", 60, "v3"));
        System.out.println(jedis.ttl("k5"));
        SetParams setParams = new SetParams();
        setParams.ex(100);
        setParams.nx();
        //插入不成功返回null
        System.out.println(jedis.set("kill", "kill", setParams));
        System.out.println(jedis.ttl("kill"));
    }

    @Test
    public void testScan(){
        this.methodScan("0");
    }

    public void methodScan(String cursor){
        // 游标初始值为0
        String key = "k*";
        ScanParams scanParams = new ScanParams();
        scanParams.match(key);
        //查询多少条
        scanParams.count(3);
        ScanResult<String> scanResult = jedis.scan(cursor, scanParams);
        cursor = scanResult.getCursor();// 返回0 说明遍历完成
        System.out.println("返回游标为："+cursor);
        List<String> list = scanResult.getResult();
        for(int m = 0;m < list.size();m++){
            String mapentry = list.get(m);
            System.out.println("查询出key:"+mapentry);
        }
        System.out.println("==========================");
        if(!"0".equals(cursor)){
            this.methodScan(cursor);
        }
    }

    @After
    public void destroy(){
        if(jedis!=null){
            jedis.close();
        }
    }



}
