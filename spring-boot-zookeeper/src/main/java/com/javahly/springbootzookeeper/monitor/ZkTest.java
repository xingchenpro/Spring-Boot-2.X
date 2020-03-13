package com.javahly.springbootzookeeper.monitor;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.*;

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
 * @desc :Zookeeper 实现事件监听
 */

public class ZkTest {

    //计数器
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static final  int TIMEOUT = 5000;

    private static final String ADDRESS = "106.13.1.171:2181";

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        ZkClient zkClient = new ZkClient(ADDRESS,TIMEOUT);
        String parentPath = "/user-service";
        //监听节点是否发生变化
        zkClient.subscribeChildChanges(parentPath, new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println(s+"节点发生了变化");
                list.forEach(t-> System.out.println(t));
            }
        });
        //监听节点值是否发生变化
        zkClient.subscribeDataChanges(parentPath+"/8081", new IZkDataListener() {
            //监听节点内容是否发生变化
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("s:"+s+"o:"+o);
            }
            //监听节点是否被删除
            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println(s+" 节点被删除");
            }
        });
        zkClient.writeData(parentPath,"article-service");
        while (true){

        }
    }
}
