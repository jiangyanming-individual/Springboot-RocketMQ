//package com.jiang.springbootrocketmq.mq;
//
//import java.io.IOException;
//import org.apache.rocketmq.client.apis.ClientConfiguration;
//import org.apache.rocketmq.client.apis.ClientConfigurationBuilder;
//import org.apache.rocketmq.client.apis.ClientException;
//import org.apache.rocketmq.client.apis.ClientServiceProvider;
//import org.apache.rocketmq.client.apis.message.Message;
//import org.apache.rocketmq.client.apis.producer.Producer;
//import org.apache.rocketmq.client.apis.producer.SendReceipt;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class ProducerExample {
//    private static final Logger logger = LoggerFactory.getLogger(ProducerExample.class);
//
//    public static void main(String[] args) throws ClientException, IOException {
//
//        // 地址
//        String endpoint = "localhost:9876";
//        // 主题
//        String topic = "TestTopic";
//        //客户端配置，构造器模式
//        ClientServiceProvider provider = ClientServiceProvider.loadService();
//        ClientConfigurationBuilder builder = ClientConfiguration.newBuilder().setEndpoints(endpoint);
//        ClientConfiguration configuration = builder.enableSsl(false).build();
//        //生产者
//        Producer producer = provider.newProducerBuilder()
//            .setTopics(topic)
//            .setClientConfiguration(configuration)
//            .build();
//
//        // 消息
//        Message message = provider.newMessageBuilder()
//            .setTopic(topic)
//            .setKeys("messageKey")
//            .setTag("messageTag")
//            .setBody("messageBody".getBytes())
//            .build();
//        try {
//            SendReceipt sendReceipt = producer.send(message);
//            logger.info("Send message successfully, messageId={}", sendReceipt.getMessageId());
//        } catch (ClientException e) {
//            logger.error("Failed to send message", e);
//        }
//        // producer.close();
//    }
//}