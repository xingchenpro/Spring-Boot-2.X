package com.javahly.springbootzookeeper.discover;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
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
        countDownLatch.await();
        String path = "/user-service";
        List<String> children = zooKeeper.getChildren(path, null, new Stat());
        for (int i = 0; i < children.size(); i++) {
            String pathChildren = path + "/" + children.get(i);
            byte[] data = zooKeeper.getData(pathChildren, null, new Stat());
            System.out.println("服务地址：" + new String(data));
        }

    }
}
