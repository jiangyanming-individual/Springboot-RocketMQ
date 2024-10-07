package com.jiang.springbootrocketmq.mq.springboot;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

// 消息监听器
@Slf4j
@Service
@RocketMQMessageListener(topic = "demo-topic",consumerGroup = "consumer-group",messageModel = MessageModel.CLUSTERING)
public class RocketMQConsumer implements RocketMQListener<String> {

    // 消费者收到消息
    @Override
    public void onMessage(String s) {
        System.out.println("收到消息：" + s);
    }

}
