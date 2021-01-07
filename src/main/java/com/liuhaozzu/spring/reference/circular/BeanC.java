package com.liuhaozzu.spring.reference.circular;

import com.liuhaozzu.spring.http.RestTemplateInvest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: liuhaozzu
 * @date: 2019-09-19 15:42
 */
@Component("beanC")
public class BeanC {
   /* @Autowired
    private BeanA beanA;*/

    @Autowired
    private RestTemplateInvest restTemplateInvest;

    @PostConstruct
    private void init() {
        //System.out.println(beanA);

        try {
            //System.out.println("restTemplateInvest>>>>" + restTemplateInvest.get("abcdefghij", 9));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "BeanC{" +
                //"beanA=" + beanA +
                '}'+" address:"+System.identityHashCode(this);
    }
}
