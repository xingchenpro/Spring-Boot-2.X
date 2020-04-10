package com.javahly.consumerservice.consumer;

import com.alibaba.fastjson.JSONObject;
import com.javahly.consumerservice.entity.DispatchEntity;
import com.javahly.consumerservice.entity.OrderEntity;
import com.javahly.consumerservice.mapper.DispatchMapper;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "orderTopic", consumerGroup = "TransactionConsumerGroup")
public class OrdeConsumer implements RocketMQListener<String> {
    @Autowired
    private DispatchMapper dispatchMapper;

    @Override
    public void onMessage(String msg) {
        OrderEntity orderEntity = JSONObject.parseObject(msg, OrderEntity.class);
        String orderId = orderEntity.getOrderId();
        // 模拟userid为=123456
        DispatchEntity dispatchEntity = new DispatchEntity(orderId, 123456L);
        dispatchMapper.insertDistribute(dispatchEntity);

    }

}
