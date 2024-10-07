//package com.jiang.springbootrocketmq.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@RestController
//public class RocketMqController {
//
//    @Value("${rocketmq.topic}")
//    private String topic;
//
//    @Autowired
//    private RocketMqProducer rocketMqProducer;
//
//    @GetMapping("/rq/send/{message}")
//    public String sendMessage(@PathVariable String message) throws ClientException, IOException {
//        rocketMqProducer.sendMessage(topic, message);
//        System.out.println("测试");
//        return "success:message->"+message;
//    }
//
//}
//
