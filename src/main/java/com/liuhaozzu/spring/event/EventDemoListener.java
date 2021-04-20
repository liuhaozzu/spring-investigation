package com.liuhaozzu.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author liuhaozzu
 * @date 2021/04/20 23:23:14
 */
@Component
public class EventDemoListener implements ApplicationListener<EventDemo> {
    @Override
    public void onApplicationEvent(EventDemo eventDemo) {
        System.out.println(eventDemo);
    }
}
