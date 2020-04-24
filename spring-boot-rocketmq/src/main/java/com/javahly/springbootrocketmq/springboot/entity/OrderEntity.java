package com.javahly.springbootrocketmq.springboot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderEntity  implements Serializable {
    private String orderId;
    private String orderName;

    public OrderEntity(String orderId, String orderName) {
        this.orderId = orderId;
        this.orderName = orderName;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderId='" + orderId + '\'' +
                ", orderName='" + orderName + '\'' +
                '}';
    }
}
