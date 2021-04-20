package com.liuhaozzu.spring.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author liuhaozzu
 * @date 2021/04/20 23:23:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventTest {

    @Resource
    private EventDemoPublisher eventDemoPublisher;

    @Test
    public void eventTest() {
        eventDemoPublisher.publish("hello event");

    }
}
