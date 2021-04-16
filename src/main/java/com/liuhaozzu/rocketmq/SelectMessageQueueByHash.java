package com.liuhaozzu.rocketmq;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.List;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

public class SelectMessageQueueByHash implements MessageQueueSelector {

    @Override
    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
        int value = arg.hashCode() % mqs.size();
        if (value < 0) {
            //when value=Integer.MIN_VALUE，Math.abs will return the nagative value
            if (value == Integer.MIN_VALUE) {
                //give a special positive value
                value = 0;
            } else {
                value = Math.abs(value);
            }
        }
        return mqs.get(value);
    }

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println("this is a test".hashCode());
    }
}

