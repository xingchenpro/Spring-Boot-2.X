package com.javahly.springbootzookeeper.order;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/3/8
 * @QQ :1136513099
 * @desc : 生成订单号
 */
public class OrderNumber {


    private static int count;

    //单个 jvm 中可以使用 sync 或者 Lock
    public  String getNumber(){

        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-d-HH-mm-ss");
        return s.format(new Date())+"-"+ ++count;
    }



}
