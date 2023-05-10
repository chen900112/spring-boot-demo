package com.springbootdemo.rabbitmqdemo.component;

import com.springbootdemo.rabbitmqdemo.config.QueueEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectMessageSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message,String route) {
        rabbitTemplate.convertAndSend("exchange.direct","route.direct."+route,message);
    }


}
