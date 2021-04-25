package com.liuhaozzu.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author liuhao01
 * @date 4/25/21 11:41 AM
 */
public class ConsumerLocal {

    @Test
    public void test1() throws MQClientException, InterruptedException {
        DefaultMQPushConsumer mqConsumer = new DefaultMQPushConsumer();
        mqConsumer.setConsumerGroup("testConsumerGroup");
        mqConsumer.subscribe("testTopic0", "*");
        mqConsumer.subscribe("testTopic1", "*");
        mqConsumer.subscribe("testTopic2", "*");
        mqConsumer.subscribe("testTopic3", "*");
        mqConsumer.setNamesrvAddr("127.0.0.1:9876");
        mqConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                msgs.forEach(msg->{
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String topic = msg.getTopic();
                    String body = new String(msg.getBody(), StandardCharsets.UTF_8);
                    System.out.println("topic:" + topic + " body=" + body);
                });
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        mqConsumer.start();

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void test2() throws MQClientException, InterruptedException {
        DefaultMQPushConsumer mqConsumer = new DefaultMQPushConsumer();
        mqConsumer.setConsumerGroup("testConsumerGroup");
        mqConsumer.subscribe("testTopic0", "*");
        mqConsumer.subscribe("testTopic1", "*");
        mqConsumer.subscribe("testTopic2", "*");
        mqConsumer.subscribe("testTopic3", "*");
        mqConsumer.setNamesrvAddr("127.0.0.1:9876");
        mqConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                msgs.forEach(msg->{
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String topic = msg.getTopic();
                    String body = new String(msg.getBody(), StandardCharsets.UTF_8);
                    System.out.println("topic:" + topic + " body=" + body);
                });
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        mqConsumer.start();
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void test3() throws MQClientException, InterruptedException {
        DefaultMQPushConsumer mqConsumer = new DefaultMQPushConsumer();
        mqConsumer.setConsumerGroup("testConsumerGroup");
        mqConsumer.subscribe("testTopic0", "*");
        mqConsumer.subscribe("testTopic1", "*");
        mqConsumer.subscribe("testTopic2", "*");
        mqConsumer.subscribe("testTopic3", "*");
        mqConsumer.setNamesrvAddr("127.0.0.1:9876");
        mqConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                msgs.forEach(msg->{
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String topic = msg.getTopic();
                    String body = new String(msg.getBody(), StandardCharsets.UTF_8);
                    System.out.println("topic:" + topic + " body=" + body);
                });
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        mqConsumer.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
    @Test
    public void test4() throws MQClientException, InterruptedException {
        DefaultMQPushConsumer mqConsumer = new DefaultMQPushConsumer();
        mqConsumer.setConsumerGroup("testConsumerGroup");
        mqConsumer.subscribe("testTopic0", "*");
        mqConsumer.subscribe("testTopic1", "*");
        mqConsumer.subscribe("testTopic2", "*");
        mqConsumer.subscribe("testTopic3", "*");
        mqConsumer.setNamesrvAddr("127.0.0.1:9876");
        mqConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                msgs.forEach(msg->{
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String topic = msg.getTopic();
                    String body = new String(msg.getBody(), StandardCharsets.UTF_8);
                    System.out.println("topic:" + topic + " body=" + body);
                });
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        mqConsumer.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
