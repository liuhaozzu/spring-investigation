package com.liuhaozzu.spring.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liuhaozzu
 * @date 2021/04/20 23:23:15
 */
@Component
public class EventDemoPublisher {
    @Resource
    private ApplicationEventPublisher eventPublisher;

    public void publish(String msg){
        EventDemo event = new EventDemo(this, msg);
        eventPublisher.publishEvent(event);
    }
}
