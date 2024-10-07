//package com.jiang.springbootrocketmq.mq;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.client.apis.ClientConfiguration;
//import org.apache.rocketmq.client.apis.ClientConfigurationBuilder;
//import org.apache.rocketmq.client.apis.ClientException;
//import org.apache.rocketmq.client.apis.ClientServiceProvider;
//import org.apache.rocketmq.client.apis.consumer.ConsumeResult;
//import org.apache.rocketmq.client.apis.consumer.FilterExpression;
//import org.apache.rocketmq.client.apis.consumer.FilterExpressionType;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Collections;
//
//@Slf4j
//@Component
//public class RocketMqConsumer {
//
//    @Value("${rocketmq.proxy}")
//    private String proxy;
//    @Value("${rocketmq.consumer.group}")
//    private String consumerGroup;
//    @Value("${rocketmq.topic}")
//    private String topic;
//
//    @Bean(name = "MqConsumer")
//    @ConditionalOnProperty(value = "rocketmq.enabled", havingValue = "true") //启用禁用
//    public void MqConsumer() {
//        ClientServiceProvider provider = ClientServiceProvider.loadService();
//        ClientConfigurationBuilder builder = ClientConfiguration.newBuilder().setEndpoints(proxy);
//        ClientConfiguration configuration = builder.build();
//        // 初始化Producer时需要设置通信配置以及预绑定的Topic。
//        try {
//            log.info("构建mq5.0消费者：proxy:{}, topic:{}, group：{}", proxy, topic, consumerGroup);
//            // 订阅消息的过滤规则，表示订阅所有Tag的消息。
//            String tag = "*";
//            FilterExpression filterExpression = new FilterExpression(tag, FilterExpressionType.TAG);
//            provider.newPushConsumerBuilder()
//                    .setClientConfiguration(configuration)
//                    // 设置消费者分组。
//                    .setConsumerGroup(consumerGroup)
//                    // 设置预绑定的订阅关系。
//                    .setSubscriptionExpressions(Collections.singletonMap(topic, filterExpression))
//                    // 设置消费监听器。
//                    .setMessageListener(messageView -> {
//                        log.info("消费消息：{}", messageView);
//                        String str = StandardCharsets.UTF_8.decode(messageView.getBody()).toString();
//                        System.out.println("消费:"+str);
//                        return ConsumeResult.SUCCESS;
//                    }).build();
//            log.info("构建mq5.0消费者成功：proxy:{}, topic:{}, group：{}", proxy, topic, consumerGroup);
//        } catch (ClientException e) {
//            log.error("构建mq5.0消费者异常：proxy:{}, topic:{}, group：{}", proxy, topic, consumerGroup, e);
//        }
//    }
//}
