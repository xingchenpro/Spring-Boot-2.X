package com.javahly.springbootrabbitmq.direct;

import com.javahly.springbootrabbitmq.quick.RabitMQConnection;
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
public class ProducerFanout {

    private static final String QUEUE_NAME = "direct_email_queue";
    /**
     * 定义交换机的名称
     */
    private static final String EXCHANGE_NAME = "direct.exchange";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        System.out.println("生产者启动成功..");
        // 1.创建我们的连接
        Connection connection = RabitMQConnection.getConnection();
        // 2.创建我们通道
        Channel channel = connection.createChannel();
        // 不需要直接关心队列，只关心交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true);
        String msg = "生产者发送消息内容" + System.currentTimeMillis();
        channel.basicPublish(EXCHANGE_NAME, "sms", null, msg.getBytes());
        channel.close();
        connection.close();
        // 如果交换机没有绑定队列，消息可能会丢失
    }
}
