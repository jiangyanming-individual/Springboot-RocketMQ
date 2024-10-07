package com.jiang.springbootrocketmq.controller;

import com.jiang.springbootrocketmq.mq.springboot.RocketMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RocketController {

    // 注入bean
    @Autowired
    private RocketMQProducer rocketMQProducer;

    @GetMapping("/sendSync")
    public String sendSync(@RequestParam String message) {
        rocketMQProducer.sendSyncMessage(message);
        return "同步消息发送成功";
    }

    @GetMapping("/sendAsync")
    public String sendAsync(@RequestParam String message) {
        rocketMQProducer.sendAsync(message);
        return "异步消息发送中";
    }

    @GetMapping("/sendOneWay")
    public String sendOneWay(@RequestParam String message) {
        rocketMQProducer.sendOneWayMessage(message);
        return "单向消息发送成功";
    }
}
