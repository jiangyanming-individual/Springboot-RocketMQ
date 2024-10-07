//package com.jiang.springbootrocketmq.mq;
//
//import lombok.extern.slf4j.Slf4j;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//
//@Slf4j
//@Component
//public class RocketMqProducer {
//
//    @Value("${rocketmq.accessKey}")
//    private String accessKey;
//    @Value("${rocketmq.secretKey}")
//    private String secretKey;
//    @Value("${rocketmq.proxy}")
//    private String endpoints;
//
//	//异步
//    public void asyncSendMessage(String topic, String messageStr) throws ClientException, InterruptedException, IOException {
//        ClientServiceProvider provider = ClientServiceProvider.loadService();
//        SessionCredentialsProvider sessionCredentialsProvider = new StaticSessionCredentialsProvider(accessKey, secretKey);
//        ClientConfiguration clientConfiguration = ClientConfiguration.newBuilder().setEndpoints(endpoints).setCredentialProvider(sessionCredentialsProvider).build();
//        Producer producer = provider.newProducerBuilder().setClientConfiguration(clientConfiguration).setTopics(new String[]{topic}).build();
//        String tag = "*";
//        Message message = provider.newMessageBuilder().setTopic(topic).setTag(tag).setBody(messageStr.getBytes(StandardCharsets.UTF_8)).build();
//        CompletableFuture<SendReceipt> future = producer.sendAsync(message);
//        ExecutorService sendCallbackExecutor = Executors.newCachedThreadPool();
//        future.whenCompleteAsync((sendReceipt, throwable) -> {
//            if (null != throwable) {
//                log.error("Failed to send message", throwable);
//            } else {
//                log.info("Send message successfully, messageId={}", sendReceipt.getMessageId());
//            }
//        }, sendCallbackExecutor);
//        Thread.sleep(Long.MAX_VALUE);
//        producer.close();
//    }
//
//	//同步
//    public void sendMessage(String topic, String messageStr) throws ClientException, IOException {
//        ClientServiceProvider provider = ClientServiceProvider.loadService();
//        SessionCredentialsProvider sessionCredentialsProvider = new StaticSessionCredentialsProvider(accessKey, secretKey);
//        ClientConfiguration clientConfiguration = ClientConfiguration
//                .newBuilder().setEndpoints(endpoints)
//                .setCredentialProvider(sessionCredentialsProvider)
//                .build();
//        Producer producer = provider.newProducerBuilder().setClientConfiguration(clientConfiguration).setTopics(new String[]{topic}).build();
//        String tag = "*";
//        System.out.println("消息内容"+messageStr);
//        Message message = provider.newMessageBuilder().setTopic(topic).setTag(tag).setBody(messageStr.getBytes(StandardCharsets.UTF_8)).build();
//        try {
//            SendReceipt sendReceipt = producer.send(message);
//            log.info("Send message successfully, messageId={}", sendReceipt.getMessageId());
//        } catch (Throwable var13) {
//            log.error("Failed to send message", var13);
//        }
//        producer.close();
//    }
//}
//
