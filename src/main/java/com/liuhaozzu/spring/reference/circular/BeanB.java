package com.liuhaozzu.spring.reference.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: liuhaozzu
 * @date: 2019-09-19 15:42
 */
@Component
public class BeanB {
    @Autowired
    private BeanC beanC;

    @PostConstruct
    private void init() {
        System.out.println(beanC);
    }

    @Override
    public String toString() {
        return "BeanB{" +
                "beanC=" + beanC +
                '}'+" address:"+System.identityHashCode(this);
    }
}
