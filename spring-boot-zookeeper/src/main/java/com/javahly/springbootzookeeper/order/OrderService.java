package com.javahly.springbootzookeeper.order;

import com.javahly.springbootzookeeper.template.Lock;
import com.javahly.springbootzookeeper.template.ZkTemplateImplLock;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/3/8
 * @QQ :1136513099
 * @desc : 生成订单号
 */
public class OrderService implements Runnable {

    private OrderNumber orderNumber = new OrderNumber();

    private Lock lock = new ZkTemplateImplLock();

    @Override
    public void run() {
        getNumber();
    }

    private void getNumber() {
        try {
            lock.getLock();
            String number = orderNumber.getNumber();
            System.out.println(Thread.currentThread().getName() + "订单号：" + number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.deleteLock();
        }
    }
}
