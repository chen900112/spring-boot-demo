package com.springbootdemo.rabbitmqdemo.component;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicMessageSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message,String routeKey) {
        rabbitTemplate.convertAndSend("exchange.topic",routeKey,message);
    }


}
