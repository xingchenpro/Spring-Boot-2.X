package com.javahly.springbootzookeeper.template;

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
public abstract class AbstractTemplateLock implements Lock {


    @Override
    public void getLock() {

        if (tryLock()) {
            System.out.println(Thread.currentThread().getName() + "获得锁成功");
        } else {
            waitLock();
            getLock();
        }
    }

    protected abstract void waitLock();

    protected abstract boolean tryLock();

    protected abstract void unLock();


    @Override
    public void deleteLock() {
        unLock();
    }
}
