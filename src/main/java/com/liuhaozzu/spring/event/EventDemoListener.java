package com.liuhaozzu.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author liuhaozzu
 * @date 2021/04/20 23:23:14
 */
@Component
public class EventDemoListener implements ApplicationListener<EventDemo> {

    @Async
    @Override
    public void onApplicationEvent(EventDemo eventDemo) {
        System.out.println("received:" + System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("received:" + eventDemo);
        System.out.println("handledSuccess:" + System.currentTimeMillis());
    }
}
