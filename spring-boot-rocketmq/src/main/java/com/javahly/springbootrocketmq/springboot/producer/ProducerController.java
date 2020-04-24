package com.javahly.springbootrocketmq.springboot.producer;

import com.javahly.springbootrocketmq.springboot.entity.OrderEntity;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @RequestMapping("/sendMsg")
    public String sendMsg() {
        OrderEntity orderEntity = new OrderEntity("123456", "Hly");
        rocketMQTemplate.convertAndSend("MyTopic", orderEntity);
        return "success";
    }

    public static void main(String[] args) {
        SpringApplication.run(ProducerController.class);
    }
}
