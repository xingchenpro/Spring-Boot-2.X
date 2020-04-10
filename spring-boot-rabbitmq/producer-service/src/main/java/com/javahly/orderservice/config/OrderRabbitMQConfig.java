package com.javahly.orderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OrderRabbitMQConfig {

    /**
     * 派单队列
     */
    public static final String ORDER_DIC_QUEUE = "order_dic_queue";
    /**
     * 补单对接
     */
    public static final String ORDER_CREATE_QUEUE = "order_create_queue";
    /**
     * 派单交换机
     */
    private static final String ORDER_EXCHANGE_NAME = "order_exchange_name";

    /**
     * 定义派单队列
     *
     * @return
     */
    @Bean
    public Queue directOrderDicQueue() {
        return new Queue(ORDER_DIC_QUEUE);
    }

    /**
     * 定义补派单队列
     *
     * @return
     */
    @Bean
    public Queue directCreateOrderQueue() {
        return new Queue(ORDER_CREATE_QUEUE);
    }


    /**
     * 定义订单交换机
     *
     * @return
     */
    @Bean
    DirectExchange directOrderExchange() {
        return new DirectExchange(ORDER_EXCHANGE_NAME);
    }


    /**
     * 派单队列与交换机绑定
     *
     * @return
     */
    @Bean
    Binding bindingExchangeOrderDicQueue() {
        return BindingBuilder.bind(directOrderDicQueue()).to(directOrderExchange()).with("orderRoutingKey");
    }

    /**
     * 补单队列与交换机绑定
     *
     * @return
     */
    @Bean
    Binding bindingExchangeCreateOrder() {
        return BindingBuilder.bind(directCreateOrderQueue()).to(directOrderExchange()).with("orderRoutingKey");
    }


}
