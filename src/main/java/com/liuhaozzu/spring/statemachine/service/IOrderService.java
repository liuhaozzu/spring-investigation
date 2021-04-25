package com.liuhaozzu.spring.statemachine.service;

import com.liuhaozzu.spring.statemachine.Order;

import java.util.Map;

/**
 * @author: liuhao01
 * @date: 4/21/21 3:29 PM
 */
public interface IOrderService {
    /**
     * 创建订单
     * @return
     */
    Order createOrder();

    /**
     * 支付
     * @param orderId
     * @return
     */
    Order pay(Long orderId);

    /**
     * 发货
     * @param orderId
     * @return
     */
    Order deliver(Long orderId);

    /**
     * 确认收货
     * @param orderId
     * @return
     */
    Order receive(Long orderId);

    /**
     * 获取订单
     * @return
     */
    Map<Long, Order> getOrders();
}
