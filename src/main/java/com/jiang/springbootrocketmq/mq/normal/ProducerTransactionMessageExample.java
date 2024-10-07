package com.jiang.springbootrocketmq.mq.normal;

import java.nio.charset.StandardCharsets;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.message.Message;
import org.apache.rocketmq.client.apis.producer.Producer;
import org.apache.rocketmq.client.apis.producer.SendReceipt;
import org.apache.rocketmq.client.apis.producer.Transaction;
import org.apache.rocketmq.client.apis.producer.TransactionChecker;
import org.apache.rocketmq.client.apis.producer.TransactionResolution;
import org.apache.rocketmq.client.java.example.ProducerSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  事务消息
 */
public class ProducerTransactionMessageExample {
    private static final Logger log = LoggerFactory.getLogger(ProducerTransactionMessageExample.class);

    private ProducerTransactionMessageExample() {
    }

    public static void main(String[] args) throws ClientException {
        final ClientServiceProvider provider = ClientServiceProvider.loadService();
        // 主题：
        String topic = "yourTransactionTopic";
        TransactionChecker checker = messageView -> {
            log.info("Receive transactional message check, message={}", messageView);
            return TransactionResolution.COMMIT;
        };
        final Producer producer = ProducerSingleton.getTransactionalInstance(checker, topic);
        // 开启事务：
        final Transaction transaction = producer.beginTransaction();
        // 发送消息内容
        byte[] body = "This is a transaction message for Apache RocketMQ".getBytes(StandardCharsets.UTF_8);
        // 二级主题
        String tag = "yourMessageTagA";
        // 消息：
        final Message message = provider.newMessageBuilder()
            .setTopic(topic)
            .setTag(tag)
            .setKeys("yourMessageKey-565ef26f5727")
            .setBody(body)
            .build();
        try {
            final SendReceipt sendReceipt = producer.send(message, transaction);
            log.info("Send transaction message successfully, messageId={}", sendReceipt.getMessageId());
        } catch (Throwable t) {
            log.error("Failed to send message", t);
            return;
        }
        transaction.commit();
    }
}
