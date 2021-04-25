package com.liuhaozzu.spring.statemachine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuhao01
 * @date 4/21/21 2:28 PM
 */
public enum OrderStatus {
    WAIT_PAYMENT(0),
    WAIT_DELIVER(10),
    WAIT_RECEIVE(20),
    FINISH(30);
    private int status;

    OrderStatus(int status) {
        this.status = status;
    }

    private static Map<Integer, OrderStatus> container = new HashMap<>();
    static {
        Arrays.stream(OrderStatus.values()).forEach(item->{
            container.put(item.status, item);
        });
    }

    public final static OrderStatus valueOf(int status) {
        return container.get(status);
    }

    public int getStatus() {
        return status;
    }}
