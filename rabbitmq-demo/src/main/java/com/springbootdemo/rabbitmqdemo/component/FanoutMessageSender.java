package com.springbootdemo.rabbitmqdemo.component;

import com.springbootdemo.rabbitmqdemo.config.QueueEnum;
import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutMessageSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("exchange.fanout","",message);
    }


}
