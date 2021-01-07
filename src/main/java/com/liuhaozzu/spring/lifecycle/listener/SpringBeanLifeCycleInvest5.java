package com.liuhaozzu.spring.lifecycle.listener;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author: liuhaozzu
 * @date: 2019-09-19 14:13
 */
@Component
public class SpringBeanLifeCycleInvest5 implements ApplicationListener<ApplicationStartingEvent> {


    @PostConstruct
    private void init() {
        System.err.println("lifecycle:PostConstruct:>>>>>>>>>>>>>>>>");
    }

    @PreDestroy
    private void preDestroy() {
        System.err.println("lifecycle:preDestroy:>>>>>>>>>>>>>>>>");

    }

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        System.err.println("lifecycle:event:>>>>>>>>>>>>>>>>"+event);
    }
}
