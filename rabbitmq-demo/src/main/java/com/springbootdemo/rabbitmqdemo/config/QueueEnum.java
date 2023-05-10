package com.springbootdemo.rabbitmqdemo.config;

import lombok.Getter;

/**
 * 消息队列枚举类
 * Created by macro on 2018/9/14.
 */
@Getter
public enum QueueEnum {
    /**
     * 扇形交换队列
     */
    QUEUE_FANOUT_CANCEL_A("exchange.fanout", "queue.fanout.a", "route.fanout.a"),
    QUEUE_FANOUT_CANCEL_B("exchange.fanout", "queue.fanout.b", "route.fanout.b"),

    /**
     * 直连交换队列
     */
    QUEUE_DIRECT_CANCEL_INFO("exchange.direct", "queue.direct.info", "route.direct.info"),
    QUEUE_DIRECT_CANCEL_ERROR("exchange.direct", "queue.direct.error", "route.direct.error"),
    QUEUE_DIRECT_CANCEL_WARNING("exchange.direct", "queue.direct.warning", "route.direct.warning");

    /**
     * 交换名称
     */
    private final String exchange;
    /**
     * 队列名称
     */
    private final String name;
    /**
     * 路由键
     */
    private final String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
