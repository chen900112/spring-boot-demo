package com.springbootdemo.rabbitmqdemo.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {
    //  ---------扇形交换机---------------------------------------------------------------------------------
    @Bean
    FanoutExchange fanoutExchange() {
        return ExchangeBuilder
                .fanoutExchange("exchange.fanout")
                .autoDelete()
                .durable(true)
                .build();
    }

    @Bean
    public Queue fanoutQueueA() {
        return new Queue("queue.fanout.a",false,false,true);
    }
    @Bean
    public Queue fanoutQueueB() {
        return new Queue("queue.fanout.b",false,false,true);
    }
    @Bean
    Binding fanoutBindingA(FanoutExchange fanoutExchange, Queue fanoutQueueA){
        return BindingBuilder
                .bind(fanoutQueueA)
                .to(fanoutExchange);
    }
    @Bean
    Binding fanoutBindingB(FanoutExchange fanoutExchange, Queue fanoutQueueB){
        return BindingBuilder
                .bind(fanoutQueueB)
                .to(fanoutExchange);
    }


    //  ---------直连交换机---------------------------------------------------------------------------------
    @Bean
    DirectExchange directExchange() {
        return ExchangeBuilder
                .directExchange("exchange.direct")
                .autoDelete()
                .durable(true)
                .build();
    }

    @Bean
    public Queue directQueueA() {
        return QueueBuilder.durable("queue.direct.a").autoDelete().build();
    }

    @Bean
    public Queue directQueueB() {
        return QueueBuilder.durable("queue.direct.b").autoDelete().build();
    }


    @Bean
    Binding directBindingInfo(DirectExchange directExchange, Queue directQueueA){
        return BindingBuilder
                .bind(directQueueA)
                .to(directExchange).with("route.direct.info");
    }


    @Bean
    Binding directBindingAInfo(DirectExchange directExchange, Queue directQueueA){
        return BindingBuilder
                .bind(directQueueA)
                .to(directExchange).with("route.direct.info");
    }


    @Bean
    Binding directBindingAError(DirectExchange directExchange, Queue directQueueA){
        return BindingBuilder
                .bind(directQueueA)
                .to(directExchange).with("route.direct.error");
    }


    @Bean
    Binding directBindingAWarning(DirectExchange directExchange, Queue directQueueA){
        return BindingBuilder
                .bind(directQueueA)
                .to(directExchange).with("route.direct.warning");
    }


    @Bean
    Binding directBindingBError(DirectExchange directExchange, Queue directQueueB){
        return BindingBuilder
                .bind(directQueueB)
                .to(directExchange).with("route.direct.error");
    }


}
