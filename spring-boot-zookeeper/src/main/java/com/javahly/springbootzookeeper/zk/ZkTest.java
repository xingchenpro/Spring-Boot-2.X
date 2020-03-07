package com.javahly.springbootzookeeper.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/3/7
 * @QQ :1136513099
 * @desc :Zookeeper 连接 Java 客户端
 */

public class ZkTest {

    //计数器
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
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
        System.out.println("zk开始创建节点...");
        //创建连接节点
        /**
         * 1、路径名称
         * 2、节点值
         * 3、节点权限
         * 4、节点类型，临时，永久
         */
        String res = zooKeeper.create("/hly", "hly".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(res);
        zooKeeper.close();
    }
}
