package com.javahly.springbootzookeeper.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/3/8
 * @QQ :1136513099
 * @desc :
 */
public class Lock {

    public static void main(String[] args) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("106.13.1.171:2181", 1000, 4000,
                new ExponentialBackoffRetry(1000, 3));
        curatorFramework.start();
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/lock");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "尝试获得锁");
                try {
                    lock.acquire();

                    System.out.println(Thread.currentThread().getName() + "获得锁");
                    Long endTime = System.currentTimeMillis() + 2000L;
                    while (System.currentTimeMillis() < endTime) {
                        Thread.sleep(800000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    lock.release();
                    System.out.println(Thread.currentThread().getName() + "释放锁 >>>");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
