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
public class Consumer {

    private static final String QUEUE_NAME = "myQueue";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1.创建连接
        Connection connection = RabitMQConnection.getConnection();
        // 2.设置通道
        Channel channel = connection.createChannel();
        //MQ每次给消费者发送消息，必须返回ack才会继续发送消息给消费者，1秒1次
        channel.basicQos(1);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("消费者获取消息:" + msg);
                //手动ACK，从队列中删掉
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        // 3.监听队列,true 默认自动签收,设置为false需要手动ack
        channel.basicConsume(QUEUE_NAME, false, defaultConsumer);

    }
}
