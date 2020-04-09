package com.javahly.springbootrabbitmq.deadLetter;

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
public class OrderConsumer {

    /**
     * 监听队列回调的方法
     *
     * @param msg
     */
    @RabbitListener(queues = "order_queue")
    public void orderConsumer(String msg) {
        System.out.println("正常订单消费者消息msg:" + msg);
    }
}

