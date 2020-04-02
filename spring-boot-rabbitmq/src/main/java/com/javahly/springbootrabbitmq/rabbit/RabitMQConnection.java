package com.javahly.springbootrabbitmq.rabbit;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

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
public class RabitMQConnection {

    public static Connection getConnection() throws IOException, TimeoutException {
        // 1.创建连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 2.设置连接地址
        connectionFactory.setHost("106.13.1.171");
        // 3.设置端口号:
        connectionFactory.setPort(5672);
        // 4.设置账号和密码
        connectionFactory.setUsername("hly");
        connectionFactory.setPassword("hly");
        // 5.设置VirtualHost
        connectionFactory.setVirtualHost("/my_visualhost");
        return connectionFactory.newConnection();
    }
}
