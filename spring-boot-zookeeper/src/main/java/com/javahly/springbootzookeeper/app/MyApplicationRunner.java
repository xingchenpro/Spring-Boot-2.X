package com.javahly.springbootzookeeper.app;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/3/7
 * @QQ :1136513099
 * @desc : SpringBoot 启动成功回调方法
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Value("${server.port}")
    private String port;

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void run(ApplicationArguments args) throws Exception {
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
        String parentPath = "/user-service";
        Stat exits = zooKeeper.exists(parentPath, null);
        if (exits == null) {
            zooKeeper.create(parentPath,"user".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        String path = "http://127.0.0.1"+port;
        zooKeeper.create(parentPath+"/"+port,path.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("服务注册成功！");
    }
}
