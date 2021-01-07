package com.liuhaozzu.spring.lifecycle.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: liuhaozzu
 * @date: 2019-09-19 14:13
 */
@Component
public class SpringBeanLifeCycleInvest4 implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        System.err.println("lifecycle:event:>>>>>>>>>>>>>>>>"+event);
    }
}
