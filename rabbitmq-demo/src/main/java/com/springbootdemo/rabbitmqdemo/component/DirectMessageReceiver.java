package com.springbootdemo.rabbitmqdemo.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectMessageReceiver {

    @RabbitListener(queues = "queue.direct.a")
    public void receiveMessageA(String message) throws InterruptedException {
        System.out.println("directMessageReceiver A: " + message);
    }

    @RabbitListener(queues = "queue.direct.b")
    public void receiveMessageB(String message) throws InterruptedException {
        System.out.println("directMessageReceiver B: " + message);
    }

}
