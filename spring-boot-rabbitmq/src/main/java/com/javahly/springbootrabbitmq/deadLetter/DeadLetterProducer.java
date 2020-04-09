package com.javahly.springbootrabbitmq.deadLetter;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/4/3
 * @QQ :1136513099
 * @desc :
 */
@RestController
public class DeadLetterProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 订单交换机
     */
    @Value("${hly.order.exchange}")
    private String orderExchange;
    /**
     * 订单路由key
     */
    @Value("${hly.order.routingKey}")
    private String orderRoutingKey;

    @RequestMapping("/sendOrder")
    public String sendOrder() {
        String msg = "发送消息";
        rabbitTemplate.convertAndSend(orderExchange, orderRoutingKey, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("10000");
                return message;
            }
        });
        return "success";
    }
}
