package com.liuhaozzu.spring.reactor;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import javax.validation.constraints.Digits;
import java.util.concurrent.TimeUnit;

/**
 * @author liuhao01
 * @date 3/15/21 2:14 PM
 */
public class BackPressure {
    @Test
    public void testBackPressure() {
        Flux.range(1, 6)
                .doOnRequest(n -> System.out.println("Request " + n + " values..."))
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        System.out.println("Subscribed and make a request...");
                        request(1);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Get value [" + value + "]");
                        request(1);
                    }
                });
    }

    @Test
    public void testScheduling(){
        Flux.range(0, 10)
                .log()
                .publishOn(Schedulers.newParallel("myParallel"))
                .subscribeOn(Schedulers.newElastic("myElastic"))
                .log()
                .blockLast();

    }
}
