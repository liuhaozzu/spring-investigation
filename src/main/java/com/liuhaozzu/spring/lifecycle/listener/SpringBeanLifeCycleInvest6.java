package com.liuhaozzu.spring.lifecycle.listener;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

/**
 * @author: liuhaozzu
 * @date: 2019-09-19 14:13
 */
@Component
public class SpringBeanLifeCycleInvest6 implements ApplicationListener<ApplicationContextEvent> {


    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
        System.err.println("lifecycle:event:>>>>>>>>>>>>>>>>"+event);
    }
}
