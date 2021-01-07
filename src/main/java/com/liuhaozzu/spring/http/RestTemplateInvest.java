package com.liuhaozzu.spring.http;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

/**
 * @author: liuhaozzu
 * @date: 2019-09-19 16:22
 */
@Component
@Validated
public class RestTemplateInvest {

    @Valid
    private final RestTemplate restTemplate;

    public RestTemplateInvest(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.setConnectTimeout(1000).setReadTimeout(1000).customizers(new ProxyCustomizer()).build();
    }

    @PostConstruct
    private void init() {
        System.out.println(System.identityHashCode(restTemplate));
    }

}
