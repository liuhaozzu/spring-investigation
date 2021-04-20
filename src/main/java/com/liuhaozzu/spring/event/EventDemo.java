package com.liuhaozzu.spring.event;


import org.springframework.context.ApplicationEvent;

/**
 * @author liuhaozzu
 * @date 2021/04/20 23:23:14
 */
public class EventDemo extends ApplicationEvent {

    private String msg;

    public EventDemo(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "EventDemo{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
