package com.liuhaozzu.spring.lifecycle.listener;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: liuhaozzu
 * @date: 2019-09-19 14:13
 */
@Component
public class SpringBeanLifeCycleInvest3 implements ApplicationListener<ApplicationFailedEvent> {


    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        System.err.println("lifecycle:event:>>>>>>>>>>>>>>>>"+event);
    }
}
