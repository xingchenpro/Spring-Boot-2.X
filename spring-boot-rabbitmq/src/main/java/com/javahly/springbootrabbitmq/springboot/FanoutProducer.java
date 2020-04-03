package com.javahly.springbootrabbitmq.springboot;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/4/3
 * @QQ :1136513099
 * @desc :
 */
@RestController
public class FanoutProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/sendMsg")
    public String sendMsg(String msg) {
        // 参数1 交换机名称 、参数2路由key  参数3 消息
        amqpTemplate.convertAndSend("myExchange", "", msg);
        return "success";
    }
}
