package com.springbootdemo.rabbitmqdemo.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicMessageReceiver {

    @RabbitListener(queues = "queue.topic.a")
    public void receiveMessageA(String message) throws InterruptedException {
        System.out.println("topicMessageReceiver A: " + message);
    }

    @RabbitListener(queues = "queue.topic.b")
    public void receiveMessageB(String message) throws InterruptedException {
        System.out.println("topicMessageReceiver B: " + message);
    }

}
