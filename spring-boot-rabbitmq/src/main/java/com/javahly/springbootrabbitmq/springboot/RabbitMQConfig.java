package com.javahly.springbootrabbitmq.springboot;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/4/3
 * @QQ :1136513099
 * @desc :
 */
@Configuration
public class RabbitMQConfig {

    /**
     * 定义交换机
     */
    private String EXCHANGE_SPRINGBOOT_NAME = "springboot.exchange";

    /**
     * 短信队列
     */
    private String FANOUT_SMS_QUEUE = "springboot_sms_queue";
    /**
     * 邮件队列
     */
    private String FANOUT_SMS_EMAIL = "springboot_email_queue";

    /**
     * 创建短信队列
     */
    @Bean
    public Queue smsQueue() {
        return new Queue(FANOUT_SMS_QUEUE);
    }

    /**
     * 创建邮件队列
     */
    @Bean
    public Queue emailQueue() {
        return new Queue(FANOUT_SMS_EMAIL);
    }

    /**
     * 创建交换机
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_SPRINGBOOT_NAME);
    }

    /**
     * 定义短信队列绑定交换机
     */
    @Bean
    public Binding smsBindingExchange(Queue smsQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(smsQueue).to(fanoutExchange);
    }

    /**
     * 定义邮件队列绑定交换机
     */
    @Bean
    public Binding emailBindingExchange(Queue emailQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(emailQueue).to(fanoutExchange);
    }
}

