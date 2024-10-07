//package com.jiang.springbootrocketmq.mq;
//
//import java.io.IOException;
//import java.util.Collections;
//import org.apache.rocketmq.client.apis.ClientConfiguration;
//import org.apache.rocketmq.client.apis.ClientException;
//import org.apache.rocketmq.client.apis.ClientServiceProvider;
//import org.apache.rocketmq.client.apis.consumer.ConsumeResult;
//import org.apache.rocketmq.client.apis.consumer.FilterExpression;
//import org.apache.rocketmq.client.apis.consumer.FilterExpressionType;
//import org.apache.rocketmq.client.apis.consumer.PushConsumer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class PushConsumerExample {
//    private static final Logger logger = LoggerFactory.getLogger(PushConsumerExample.class);
//
//    private PushConsumerExample() {
//
//    }
//
//    public static void main(String[] args) throws ClientException, IOException, InterruptedException {
//        //客户端：
//        final ClientServiceProvider provider = ClientServiceProvider.loadService();
//
//        // 地址
//        String endpoints = "localhost:9876";
//        // 配置地址
//        ClientConfiguration clientConfiguration = ClientConfiguration.newBuilder()
//            .setEndpoints(endpoints).enableSsl(false)
//            .build();
//        String tag = "*";
//        FilterExpression filterExpression = new FilterExpression(tag, FilterExpressionType.TAG);
//        // 消费者组
//        String consumerGroup = "YourConsumerGroup";
//        // 消费主题
//        String topic = "TestTopic";
//
//        // 消费者
//        PushConsumer pushConsumer = provider.newPushConsumerBuilder()
//            .setClientConfiguration(clientConfiguration)
//            .setConsumerGroup(consumerGroup)
//            .setSubscriptionExpressions(Collections.singletonMap(topic, filterExpression))
//            .setMessageListener(messageView -> {
//                logger.info("Consume message successfully, messageId={}", messageView.getMessageId());
//                return ConsumeResult.SUCCESS;
//            })
//            .build();
//        Thread.sleep(Long.MAX_VALUE);
//        // pushConsumer.close();
//    }
//}