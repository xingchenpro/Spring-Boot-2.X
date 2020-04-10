package com.javahly.producerservice.listener;

import com.alibaba.fastjson.JSONObject;
import com.javahly.producerservice.entity.OrderEntity;
import com.javahly.producerservice.mapper.OrderMapper;
import com.javahly.producerservice.utils.TransationalUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

@Slf4j
@Component
@RocketMQTransactionListener(txProducerGroup = "TransactionProducer")
public class SyncProducerListener implements RocketMQLocalTransactionListener {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private TransationalUtils transationalUtils;

    /**
     * 执行本地的事务
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {

        MessageHeaders headers = msg.getHeaders();
        Object object = headers.get("msg");
        if (object == null) {
            return null;
        }
        String orderMsg = (String) object;
        OrderEntity orderEntity = JSONObject.parseObject(orderMsg, OrderEntity.class);
        TransactionStatus begin = null;
        try {
            begin = transationalUtils.begin();
            int result = orderMapper.addOrder(orderEntity);
            //int i = 1/0;
            transationalUtils.commit(begin);
            if (result <= 0) {
                return RocketMQLocalTransactionState.ROLLBACK;
            }
            // 告诉我们的Broke可以消费者该消息
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            if (begin != null) {
                transationalUtils.rollback(begin);
                return RocketMQLocalTransactionState.ROLLBACK;
            }
        }
        //add.Order
        return null;
    }

    /**
     * 提供给我们的Broker定时检查
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        MessageHeaders headers = msg.getHeaders();
        Object object = headers.get("msg");
        if (object == null) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        String orderMsg = (String) object;
        OrderEntity orderEntity = JSONObject.parseObject(orderMsg, OrderEntity.class);
        String orderId = orderEntity.getOrderId();
        // 直接查询我们的数据库
        OrderEntity orderDbEntity = orderMapper.findOrderId(orderId);
        if (orderDbEntity == null) {
            return RocketMQLocalTransactionState.UNKNOWN;
        }
        return RocketMQLocalTransactionState.COMMIT;
    }
}
