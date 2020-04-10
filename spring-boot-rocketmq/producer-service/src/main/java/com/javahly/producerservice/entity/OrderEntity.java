/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.javahly.producerservice.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderEntity implements Serializable {

    private Long id;
    // 订单名称
    private String name;
    // 订单时间
    private Date orderCreatetime;
    // 下单金额
    private Double orderMoney;
    // 订单状态
    private int orderState;
    // 商品id
    private Long commodityId;

    // 订单id
    private String orderId;

    public OrderEntity(Long id, String name, Date orderCreatetime, Double orderMoney, int orderState, Long commodityId, String orderId) {
        this.id = id;
        this.name = name;
        this.orderCreatetime = orderCreatetime;
        this.orderMoney = orderMoney;
        this.orderState = orderState;
        this.commodityId = commodityId;
        this.orderId = orderId;
    }

    public OrderEntity() {

    }
}
