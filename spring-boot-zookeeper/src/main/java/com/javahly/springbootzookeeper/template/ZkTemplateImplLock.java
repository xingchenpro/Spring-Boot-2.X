package com.javahly.springbootzookeeper.template;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/3/8
 * @QQ :1136513099
 * @desc :
 */
public class ZkTemplateImplLock extends AbstractTemplateLock {

    //计数器
    private static CountDownLatch countDownLatch;

    private static final int TIMEOUT = 5000;

    //会话超时时间，与客户端失去连接，临时节点多久消失
    private static final int SESSION = 2000;

    private static final String ADDRESS = "106.13.1.171:2181";


    ZkClient zkClient = new ZkClient(ADDRESS, SESSION, TIMEOUT);

    private String pathLock = "/lock";


    @Override
    protected boolean tryLock() {

        try {
            zkClient.createEphemeral(pathLock);
            return true;
        } catch (RuntimeException e) {
            //e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void waitLock() {

        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
        };
        zkClient.subscribeDataChanges(pathLock, iZkDataListener);

        if (countDownLatch == null) {
            countDownLatch = new CountDownLatch(1);
        }
        try {
            //如果节点不为0则等待，表示其他线程占有锁
            countDownLatch.await();
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        zkClient.unsubscribeDataChanges(pathLock, iZkDataListener);
    }

    @Override
    protected void unLock() {
        if (zkClient != null) {
            zkClient.close();
            System.out.println(Thread.currentThread().getName() + "释放锁");
        }
    }
}
