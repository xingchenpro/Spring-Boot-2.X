package com.javahly.springbootrabbitmq.ack;


import com.javahly.springbootrabbitmq.quick.RabitMQConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

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

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 1.创建连接
        Connection connection = RabitMQConnection.getConnection();
        // 2.设置通道
        Channel channel = connection.createChannel();
        //开启消息确认机制
        channel.confirmSelect();
        // 3.设置消息
        String msg = "你好";
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            if (channel.waitForConfirms()) {
                System.out.println("生产者投递消息成功" + msg);
            } else {
                System.out.println("生产者投递消息失败");
            }
        }
        channel.close();
        connection.close();
    }
}
