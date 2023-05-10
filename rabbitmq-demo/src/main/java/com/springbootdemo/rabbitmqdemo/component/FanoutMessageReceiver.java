package com.springbootdemo.rabbitmqdemo.component;

import com.springbootdemo.rabbitmqdemo.config.QueueEnum;
import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutMessageReceiver {

    @RabbitListener(queues = {"queue.fanout.a","queue.fanout.b"})
    public void receiveMessage(String message) throws InterruptedException {
        System.out.println("fanoutReceiveMessage: " + message);
    }

}
