package com.jiang.springbootrocketmq.mq.normal;

import java.nio.charset.StandardCharsets;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.message.Message;
import org.apache.rocketmq.client.apis.producer.Producer;
import org.apache.rocketmq.client.apis.producer.SendReceipt;
import org.apache.rocketmq.client.java.example.ProducerSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 顺序消息
 */
public class ProducerFifoMessageExample {
    private static final Logger log = LoggerFactory.getLogger(ProducerFifoMessageExample.class);

    private ProducerFifoMessageExample() {
    }

    public static void main(String[] args) throws ClientException {
        final ClientServiceProvider provider = ClientServiceProvider.loadService();
        // 主题
        String topic = "yourFifoTopic";
        final Producer producer = ProducerSingleton.getInstance(topic);
        // 内容
        byte[] body = "This is a FIFO message for Apache RocketMQ".getBytes(StandardCharsets.UTF_8);
        // 二级主题
        String tag = "yourMessageTagA";
        final Message message = provider.newMessageBuilder()
            // 主题：
            .setTopic(topic)
            // 二级主题
            .setTag(tag)
            // 密钥
            .setKeys("yourMessageKey-1ff69ada8e0e")
            // 消费组：
            .setMessageGroup("yourMessageGroup0")
            .setBody(body)
            .build();
        try {
            final SendReceipt sendReceipt = producer.send(message);
            log.info("Send message successfully, messageId={}", sendReceipt.getMessageId());
        } catch (Throwable t) {
            log.error("Failed to send message", t);
        }
        // Close the producer when you don't need it anymore.
        // You could close it manually or add this into the JVM shutdown hook.
        // producer.close();
    }
}