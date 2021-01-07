package com.liuhaozzu.spring.reference.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: liuhaozzu
 * @date: 2019-09-19 15:42
 */
@Component
public class BeanA {
    @Autowired
    private BeanB beanB;

    @PostConstruct
    private void init() {
        System.out.println(beanB);
    }

    @Override
    public String toString() {
        return "BeanA{" +
                "beanB=" + beanB +
                '}'+" address:"+System.identityHashCode(this);
    }
}
