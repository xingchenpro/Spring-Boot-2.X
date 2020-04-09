package com.javahly.springbootrabbitmq.deadLetter;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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
public class DeadLetterMQConfig {
    /**
     * 订单交换机
     */
    @Value("${hly.order.exchange}")
    private String orderExchange;

    /**
     * 订单队列
     */
    @Value("${hly.order.queue}")
    private String orderQueue;

    /**
     * 订单路由key
     */
    @Value("${hly.order.routingKey}")
    private String orderRoutingKey;
    /**
     * 死信交换机
     */
    @Value("${hly.dlx.exchange}")
    private String dlxExchange;

    /**
     * 死信队列
     */
    @Value("${hly.dlx.queue}")
    private String dlxQueue;
    /**
     * 死信路由
     */
    @Value("${hly.dlx.routingKey}")
    private String dlxRoutingKey;

    /**
     * 声明死信交换机
     */
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(dlxExchange);
    }

    /**
     * 声明死信队列
     */
    @Bean
    public Queue dlxQueue() {
        return new Queue(dlxQueue);
    }

    /**
     * 声明订单业务交换机
     */
    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(orderExchange);
    }

    /**
     * 绑定死信队列到死信交换机
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(dlxQueue())
                .to(dlxExchange())
                .with(dlxRoutingKey);
    }

    /**
     * 声明订单队列
     */
    @Bean
    public Queue orderQueue() {
        // 订单队列绑定死信交换机
        Map<String, Object> arguments = new HashMap<>(2);
        arguments.put("x-dead-letter-exchange", dlxExchange);
        arguments.put("x-dead-letter-routing-key", dlxRoutingKey);
        return new Queue(orderQueue, true, false, false, arguments);
    }

    /**
     * 绑定订单队列到订单交换机
     */
    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue())
                .to(orderExchange())
                .with(orderRoutingKey);
    }
}
