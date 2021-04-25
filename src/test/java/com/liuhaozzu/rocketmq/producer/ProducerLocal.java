package com.liuhaozzu.rocketmq.producer;

import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liuhao01
 * @date 4/25/21 11:20 AM
 */
public class ProducerLocal {
    public static void main(String[] args) throws MQClientException {

        DefaultMQProducer producer = new DefaultMQProducer("testProducerGroupName");
        producer.setNamesrvAddr("127.0.0.1:9876");

        producer.start();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(30000));

        try {

            for (int i = 0; i < 300000; i++) {
                int j=i;
                executor.execute(()->{
                    String message = "send message" + j;
                    final Message msg = new Message("testTopic" + j % 4, "tag",// tag
                            message.getBytes());// body
                    SendResult sendResult = null;
                    try {
                        Thread.sleep(20);
                        sendResult = producer.send(msg);
                    } catch (MQClientException e) {
                        e.printStackTrace();
                    } catch (RemotingException e) {
                        e.printStackTrace();
                    } catch (MQBrokerException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(sendResult);
                });
            }
            Thread.sleep(Integer.MAX_VALUE);

            producer.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
