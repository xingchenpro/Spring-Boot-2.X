package com.hly.springBootRedis.lock;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/3/8
 * @QQ :1136513099
 * @desc :
 */
public class Test {

    public static void main(String[] args) {
        LockService lockService = new LockService();
        for (int i = 0; i < 20; i++) {
           new Thread(()->lockService.test()).start();
        }
    }
}
