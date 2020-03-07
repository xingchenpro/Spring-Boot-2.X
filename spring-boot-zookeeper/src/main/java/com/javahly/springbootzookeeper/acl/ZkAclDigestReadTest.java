package com.javahly.springbootzookeeper.acl;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/3/7
 * @QQ :1136513099
 * @desc :读取节点的值
 */

public class ZkAclDigestReadTest {

    //计数器
    private static CountDownLatch countDownLatch= new CountDownLatch(1);

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException, NoSuchAlgorithmException {
        /**
         * 1、连接地址
         * 2、超时时间
         * 3、事件通知
         */
        ZooKeeper zooKeeper = new ZooKeeper("106.13.1.171:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                //获取连接状态
                Event.KeeperState state = watchedEvent.getState();
                if (state == Event.KeeperState.SyncConnected) {
                    System.out.println("zk连接成功");
                    countDownLatch.countDown();
                }
            }
        });
        //计数器结果为0才执行
        System.out.println("zk正在连接...");
        countDownLatch.await();
        zooKeeper.addAuthInfo("digest","guest:guest123".getBytes());
        byte[] bytes = zooKeeper.getData("/hly",null,new Stat());
        System.out.println(new String(bytes));
        zooKeeper.close();
    }
}
