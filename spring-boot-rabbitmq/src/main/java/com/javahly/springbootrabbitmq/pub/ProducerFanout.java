package com.javahly.springbootrabbitmq.pub;

import com.javahly.springbootrabbitmq.rabbit.RabitMQConnection;
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
public class ProducerFanout {

    /**
     * 定义交换机的名称
     */
    private static final String EXCHANGE_NAME = "myExchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        //  创建Connection
        Connection connection = RabitMQConnection.getConnection();
        // 创建Channel
        Channel channel = connection.createChannel();
        // 通道关联交换机，true表示持久化
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout", true);
        String msg = "发送消息";
        channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
        channel.close();
        connection.close();
    }

}
