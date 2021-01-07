package com.liuhaozzu.spring.lifecycle.listener;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: liuhaozzu
 * @date: 2019-09-19 14:13
 */
@Component
public class SpringBeanLifeCycleInvest implements ApplicationListener<ApplicationPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {
        System.err.println("lifecycle:event:>>>>>>>>>>>>>>>>"+applicationPreparedEvent);
    }
}
