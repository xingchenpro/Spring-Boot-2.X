package com.javahly.springbootzookeeper.order;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/3/8
 * @QQ :1136513099
 * @desc :
 */
public class Product {


    public static void main(String[] args){

        for (int i = 0; i <10 ; i++) {
            new Thread(new OrderService()).start();
        }
    }
}
