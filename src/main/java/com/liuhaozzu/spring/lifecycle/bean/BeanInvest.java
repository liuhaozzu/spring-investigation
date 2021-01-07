package com.liuhaozzu.spring.lifecycle.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: liuhaozzu
 * @date: 2019-09-19 14:28
 */
@Component
public class BeanInvest implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("lifecycle:>>>>>>>>>>>>>>>>"+"postProcessBeforeInitialization:"+beanName+":"+bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("lifecycle:>>>>>>>>>>>>>>>>"+"postProcessAfterInitialization:"+beanName+":"+bean);
        return bean;
    }
}
