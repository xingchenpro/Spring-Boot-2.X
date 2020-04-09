package com.javahly.springbootrabbitmq.quick;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/4/3
 * @QQ :1136513099
 * @desc :
 */
public class Producer {

    private static final String QUEUE_NAME = "first_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1.创建连接
        Connection connection = RabitMQConnection.getConnection();
        // 2.设置通道
        Channel channel = connection.createChannel();
        // 3.设置消息
        String msg = "发送第一个消息";
        System.out.println("msg:" + msg);
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        channel.close();
        connection.close();
    }
}
