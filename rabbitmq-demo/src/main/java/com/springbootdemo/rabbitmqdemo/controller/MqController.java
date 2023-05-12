package com.springbootdemo.rabbitmqdemo.controller;


import com.springbootdemo.democommon.api.CommonResult;
import com.springbootdemo.rabbitmqdemo.component.DirectMessageSender;
import com.springbootdemo.rabbitmqdemo.component.FanoutMessageSender;
import com.springbootdemo.rabbitmqdemo.component.TopicMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mq")
public class MqController {
    @Autowired
    private FanoutMessageSender fanoutMessageSender;

    @Autowired
    private DirectMessageSender directMessageSender;

    @Autowired
    private TopicMessageSender topicMessageSender;

    @RequestMapping(value = "/fanoutQueue", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult fanoutQueue(@RequestParam String message) {
        fanoutMessageSender.sendMessage(message);
        return CommonResult.success("ok");

    }


    @RequestMapping(value = "/directQueue", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult fanoutQueue(@RequestParam String message,@RequestParam String route) {
        directMessageSender.sendMessage(message,route);
        return CommonResult.success("ok");

    }

    @RequestMapping(value = "/topicQueue", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult topicQueue(@RequestParam String message,@RequestParam String routeKey) {
        topicMessageSender.sendMessage(message,routeKey);
        return CommonResult.success("ok");

    }

}
