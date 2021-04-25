package com.liuhaozzu.spring.statemachine.service;

import com.liuhaozzu.spring.statemachine.Order;
import com.liuhaozzu.spring.statemachine.OrderEvent;
import com.liuhaozzu.spring.statemachine.OrderStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuhao01
 * @date 4/21/21 3:28 PM
 */
@Service
public class OrderServiceImpl implements IOrderService {

    //@Resource
    //private OrderMapper orderMapper;

    //@Resource
    //private StateMachine<OrderStatus, OrderEvent> orderStateMachine;

    public static final String stateMachineId = "orderStateMachine";

    @Resource
    private StateMachineFactory<OrderStatus, OrderEvent> orderStateMachineFactory;

    @Resource
    private StateMachinePersister<OrderStatus, OrderEvent, Order> persister;

    private Long id = 1L;
    private Map<Long, Order> orders = new HashMap<>();

    @Override
    public Order createOrder() {
        Order order = new Order();
        order.setStatus(OrderStatus.WAIT_PAYMENT.getStatus())
                .setOrderId(id++);
        orders.put(order.getOrderId(), order);
        return order;
    }

    @Override
    public Order pay(Long orderId) {
        Order order = orders.get(orderId);
        System.out.println("threadName=" + Thread.currentThread().getName() + " 尝试支付 orderId=" + orderId);
        Message<OrderEvent> message = MessageBuilder.withPayload(OrderEvent.PAYED)
                .setHeader("order", order)
                .build();
        if (!sendEvent(message, order)) {
            System.out.println("threadName=" + Thread.currentThread().getName() + " 支付失败，状态异常 orderId=" + orderId);
        }
        return orders.get(orderId);
    }

    @Override
    public Order deliver(Long orderId) {
        Order order = orders.get(orderId);
        System.out.println("threadName=" + Thread.currentThread().getName() + " 尝试发货 orderId=" + orderId);
        if (!sendEvent(MessageBuilder.withPayload(OrderEvent.DELIVERY).setHeader("order", order).build(), orders.get(orderId))) {
            System.out.println("threadName=" + Thread.currentThread().getName() + " 发货失败，状态异常 orderId=" + orderId);
        }
        return orders.get(orderId);
    }

    @Override
    public Order receive(Long orderId) {
        Order order = orders.get(orderId);
        System.out.println("threadName=" + Thread.currentThread().getName() + " 尝试收货 orderId=" + orderId);
        if (!sendEvent(MessageBuilder.withPayload(OrderEvent.RECEIVED).setHeader("order", order).build(), orders.get(orderId))) {
            System.out.println("threadName=" + Thread.currentThread().getName() + " 收货失败，状态异常 orderId=" + orderId);
        }
        return orders.get(orderId);
    }

    @Override
    public Map<Long, Order> getOrders() {
        return orders;
    }

    private boolean sendEvent(Message<OrderEvent> message, Order order) {
        synchronized (String.valueOf(order.getOrderId()).intern()) {
            boolean result = false;
            StateMachine<OrderStatus, OrderEvent> orderStateMachine = orderStateMachineFactory.getStateMachine(stateMachineId);
            System.out.println("orderId=" + order.getOrderId() + " 状态机 orderStateMachine" + orderStateMachine);
            try {
                orderStateMachine.start();
                //尝试恢复状态机
                persister.restore(orderStateMachine, order);
                System.out.println("orderId=" + order.getOrderId() + " 状态机 orderStateMachine Id=" + orderStateMachine.getId());
                //添加延迟，用于线程安全测试
                Thread.sleep(1000);
                result = orderStateMachine.sendEvent(message);
                persister.persist(orderStateMachine, order);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                orderStateMachine.stop();
            }
            return result;
        }
    }
}
