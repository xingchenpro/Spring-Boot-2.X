package com.javahly.springbootrabbitmq.springboot;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/4/3
 * @QQ :1136513099
 * @desc :
 */
@Component
@RabbitListener(queues = "youQueue")
public class FanoutEmailConsumer {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("邮件消费者消息msg:" + msg);
    }
}

