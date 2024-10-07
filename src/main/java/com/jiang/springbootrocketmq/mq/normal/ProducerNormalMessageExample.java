
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
 * 正常消息
 */
public class ProducerNormalMessageExample {
    private static final Logger log = LoggerFactory.getLogger(ProducerNormalMessageExample.class);

    private ProducerNormalMessageExample() {
        // 生产者
    }
    public static void main(String[] args) throws ClientException {
        final ClientServiceProvider provider = ClientServiceProvider.loadService();
        // 主题
        String topic = "yourNormalTopic";
        final Producer producer = ProducerSingleton.getInstance(topic);
        // 消息内容
        byte[] body = "This is a normal message for Apache RocketMQ".getBytes(StandardCharsets.UTF_8);
        // 二级主题
        String tag = "yourMessageTagA";
        final Message message = provider.newMessageBuilder()
            // Set topic for the current message.
            .setTopic(topic)
            // Message secondary classifier of message besides topic.
            .setTag(tag)
            // Key(s) of the message, another way to mark message besides message id.
            .setKeys("yourMessageKey-1c151062f96e")
            .setBody(body)
            .build();
        try {
            // 发送消息：
            final SendReceipt sendReceipt = producer.send(message);
            log.info("Send message successfully, messageId={}", sendReceipt.getMessageId());
        } catch (Throwable t) {
            log.error("Failed to send message", t);
        }
    }
}