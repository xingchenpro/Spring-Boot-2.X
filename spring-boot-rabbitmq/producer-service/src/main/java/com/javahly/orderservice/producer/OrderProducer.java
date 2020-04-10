package com.javahly.orderservice.producer;

import com.alibaba.fastjson.JSONObject;
import com.javahly.orderservice.entity.OrderEntity;
import com.javahly.orderservice.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@Slf4j
public class OrderProducer implements RabbitTemplate.ConfirmCallback {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 基于MQ发送投递订单消息
     */
    @Transactional
    public String sendOrder() {
        // 1.先创建我们订单信息
        String orderId = System.currentTimeMillis() + "";
        OrderEntity orderEntity = createOrder(orderId);
        // 2.添加到我们的数据库中
        int result = orderMapper.addOrder(orderEntity);
        if (result <= 0) {
            return null;
        }
        // 3.订单数据库插入成功的情况下, 使用MQ异步发送派单信息
        String msgJson = JSONObject.toJSONString(orderEntity);
        sendMsg(msgJson);
        int i = 1 / 0;
        return orderId;
    }


    public void sendMsg(String msgJson) {
        // 设置生产者消息确认机制
        this.rabbitTemplate.setMandatory(true);
        this.rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(msgJson);
        String orderExchange = "order_exchange_name";
        String orderRoutingKey = "orderRoutingKey";
        rabbitTemplate.convertAndSend(orderExchange, orderRoutingKey, msgJson, correlationData);
    }


    public OrderEntity createOrder(String orderId) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setName("我的订单");
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


    /**
     * correlationData 投递失败回调的消息
     *
     * @param correlationData
     * @param ack             true 投递到MQ成功 如果是为false情况下 消息投递失败
     * @param s
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        String msg = correlationData.getId();
        if (!ack) {
            log.info("<<<往MQ投递消息失败>>>>:" + msg);
            // 采用递归算法重试
            sendMsg(msg);
            return;
        }
        log.info("<<<往MQ投递消息成功>>>>:" + msg);
        // 生产者投递多次还是is的情况下应该 人工记录
    }
}
