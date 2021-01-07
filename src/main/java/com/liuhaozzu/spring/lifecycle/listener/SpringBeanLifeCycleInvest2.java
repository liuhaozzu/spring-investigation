package com.liuhaozzu.spring.lifecycle.listener;

import com.liuhaozzu.spring.reference.circular.BeanC;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author: liuhaozzu
 * @date: 2019-09-19 14:13
 */
@Component
public class SpringBeanLifeCycleInvest2 implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.err.println("lifecycle:event:>>>>>>>>>>>>>>>>"+event);

        ApplicationContext context = event.getApplicationContext();
        System.out.println(context.getBean(BeanC.class));

        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        System.out.println(System.identityHashCode(restTemplate));

            try {
                ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://app.zhuanzhuan.com/zzgift/getfeedlistv2", String.class);
                System.out.println(responseEntity);
                System.out.println(responseEntity.getStatusCode());
                System.out.println(responseEntity.getStatusCode().is2xxSuccessful());
                System.out.println(responseEntity.getBody());

                ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(responseEntity.getStatusCode());

                bodyBuilder.contentLength(100).body("加句话");
                ResponseEntity<Class<String>> responseEntity1 = bodyBuilder.build();
                System.out.println(responseEntity1.getBody());
            } catch (RestClientException e) {
                e.printStackTrace();
            }


    }

}
