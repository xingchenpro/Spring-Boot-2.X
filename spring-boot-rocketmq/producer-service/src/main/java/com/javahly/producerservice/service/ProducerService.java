package com.javahly.producerservice.service;

import com.alibaba.fastjson.JSONObject;

import com.javahly.producerservice.entity.OrderEntity;
import com.javahly.producerservice.mapper.OrderMapper;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProducerService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public String saveOrder() {
        // 提前生成我们的订单id
        String orderId = System.currentTimeMillis() + "";

        /**
         * 1.提前生成我们的半消息
         * 2.半消息发送成功之后，在执行我们的本地事务
         */
        OrderEntity orderEntity = createOrder(orderId);
        String msg = JSONObject.toJSONString(orderEntity);
        MessageBuilder<String> stringMessageBuilder = MessageBuilder.withPayload(msg);
        stringMessageBuilder.setHeader("msg", msg);
        Message message = stringMessageBuilder.build();
        // 该消息不允许被消费者消费 TransactionProducer 需要和回调的方法一致
        rocketMQTemplate.sendMessageInTransaction("TransactionProducer",
                "orderTopic", message, null);
        return orderId;

    }

    public OrderEntity createOrder(String orderId) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setName("Hello World");
        orderEntity.setOrderCreatetime(new Date());
        // 价格是300元
        orderEntity.setOrderMoney(300d);
        // 状态为 未支付
        orderEntity.setOrderState(0);
        Long commodityId = 30L;
        // 商品id
        orderEntity.setCommodityId(commodityId);
        orderEntity.setOrderId(orderId);
        return orderEntity;
    }
}
