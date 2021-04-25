package com.liuhaozzu.spring.statemachine;

import com.liuhaozzu.spring.statemachine.service.IOrderService;
import com.liuhaozzu.spring.statemachine.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author liuhao01
 * @date 4/21/21 3:48 PM
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    @Resource
    private IOrderService orderService;

    @Test
    public void testMultThread() {
        orderService.createOrder();
        orderService.createOrder();

        orderService.pay(1L);
        orderService.pay(1L);
        orderService.pay(1L);



    }
}
