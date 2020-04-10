package com.javahly.orderservice.consumer;

import com.alibaba.fastjson.JSONObject;

import com.javahly.orderservice.entity.OrderEntity;
import com.javahly.orderservice.mapper.OrderMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: CreateOrderConsumer
 * @description: 每特教育独创第五期互联网架构课程
 * @date 2019/12/1722:08
 */
@Component
public class CreateOrderConsumer {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 订阅补单队列
     *
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = "order_create_queue")
    public void createOrderConsumer(Message message, Channel channel) throws IOException {
        // 1.获取消息
        String msg = new String(message.getBody());
        // 2.获取order对象
        OrderEntity orderEntity = JSONObject.parseObject(msg, OrderEntity.class);
        // 3.获取订单号码
        String orderId = orderEntity.getOrderId();
        // 4.根据订单号码查询是否存在
        OrderEntity dbOrderEntity = orderMapper.findOrderId(orderId);
        if (dbOrderEntity != null) {
            // 手动ack 删除该消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            return;

        }
        // 说明该订单不存在，创建该订单
        // 2.添加到我们的数据库中
        int result = orderMapper.addOrder(orderEntity);
        if (result > 0) {
            // 手动ack 删除该消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    /***
     * 补单的消费不应该和 订单生产者放到一个服务器节点
     * 补单消费者如果不存在的情况下 队列缓存补单消息
     * 补偿分布式事务解决框架 思想最终一致性
     */
}
