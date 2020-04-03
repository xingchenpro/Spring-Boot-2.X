package com.javahly.springbootrabbitmq.rabbit;


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

    private static final String QUEUE_NAME = "myQueue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 1.创建连接
        Connection connection = RabitMQConnection.getConnection();
        // 2.设置通道
        Channel channel = connection.createChannel();
        // 3.设置消息
        String msg = "生产者生产消息";
        for (int i = 0; i < 10; i++) {
            //开启生产者确认消息投递
            channel.confirmSelect();
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            //生产者确认投递成功
            if (channel.waitForConfirms()) {
                System.out.println("msg:" + msg);
            }
        }
        channel.close();
        connection.close();
    }
}
