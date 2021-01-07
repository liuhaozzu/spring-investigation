package com.liuhaozzu.spring;

import com.liuhaozzu.spring.reference.circular.BeanA;
import com.liuhaozzu.spring.reference.circular.BeanB;
import com.liuhaozzu.spring.reference.circular.BeanC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public BeanA beanA() {
        System.out.println("Configuration BeanA");
        return new BeanA();
    }
    @Bean
    public BeanB beanB() {
        System.out.println("Configuration BeanB");
        return new BeanB();
    }

    @Bean
    public BeanC beanC() {
        System.out.println("Configuration BeanC");
        BeanC beanC = new BeanC();
        System.out.println(beanC);
        return beanC;

    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);//单位为ms
        factory.setConnectTimeout(5000);//单位为ms
        return factory;
    }


}