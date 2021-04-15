package com.liuhaozzu.spring.redis;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuhao01
 * @date 2020-11-01 22:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate<String, String> redisTemplate;





    @Test
    public void kvTest() {
        redisTemplate.boundValueOps("redis").set("key1");
        String str = (String) redisTemplate.boundValueOps("redis").get();
        System.out.println(str);

    }

    @Test
    public void setTest() {

        for (long i = 0; i < 10000000000L; i++) {
            Long r = redisTemplate.opsForSet().add("abc", i + "");
            System.out.println(r);
            Set<String> set = redisTemplate.opsForSet().members("abc");
            System.out.println(set);
            System.out.println(set.size());

        }


    }
    @Test
    public void setTest2() {

        Set<String> set = redisTemplate.opsForSet().members("abc");

        System.out.println(set);
        Boolean r = redisTemplate.opsForSet().isMember("abc", "572");
        System.out.println(r);

    }



}
