package com.liuhaozzu.spring.statemachine;

import java.util.StringJoiner;

/**
 * @author liuhao01
 * @date 4/21/21 2:56 PM
 */
public class Order {
    private Long orderId;
    private Integer status;
    private String productName;
    private Long price;

    public Long getOrderId() {
        return orderId;
    }

    public Order setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public Order setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public Order setPrice(Long price) {
        this.price = price;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Order setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
                .add("orderId=" + orderId)
                .add("status=" + status)
                .add("productName='" + productName + "'")
                .add("price=" + price)
                .toString();
    }
}
