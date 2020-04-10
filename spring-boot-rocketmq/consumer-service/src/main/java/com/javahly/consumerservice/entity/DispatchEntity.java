package com.javahly.consumerservice.entity;

import lombok.Data;

@Data
public class DispatchEntity {

    private Long id;
    // 订单号
    private String orderId;

    // 派单id
    private Long userId;

    public DispatchEntity(String orderId, Long userId) {
        this.orderId = orderId;
        this.userId = userId;
    }
}
