package com.liuhaozzu.spring.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import sun.jvmstat.monitor.MonitoredVmUtil;

import java.time.LocalDateTime;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: liuhaozzu
 * @date: 2019-09-20 10:45
 */
public class GuavaCacheInvest {
    static ListeningExecutorService backgroundRefreshPools =
            MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(1));

    static CacheLoader<String,String> cacheLoader=new CacheLoader<String, String>() {
        private int count = 0;

        @Override
        public String load(String key) throws Exception {
            long start = System.currentTimeMillis();
            String value = doLoad(key, "load>>> key=" + key);
            System.out.println("load>>> key=" + key + " value=" + value + " cost=" + (System.currentTimeMillis() - start));
            return value;
        }

        private String doLoad(String s,String flag) throws InterruptedException {
            System.out.println(flag + " now=" + LocalDateTime.now().getSecond());
            if (++count > 3) {
                System.out.println("load is now sleep "+count);
                Thread.sleep(count * 1000);
                System.out.println("load is now wakeup..."+count);
            }
            return "abc:" + s + "count=" + count;
        }

        @Override
        public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
            ListenableFutureTask<String> task = ListenableFutureTask.create(new Callable<String>() {

                @Override
                public String call() {
                    try {
                        System.out.println("thread Lock status:"+Thread.currentThread());
                        return load( key);
                    } catch (Exception e) {
                    }
                    return oldValue;
                }
            });
            ThreadPoolUtil.getInstance().execute(task);//这里将这个task放到自定义的线程池中去执行，返回一个futrue，可以通过future获取线程执行获取的值
            return task;
        }

    };

    private static LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
            .concurrencyLevel(1)
            .expireAfterWrite(10,TimeUnit.SECONDS)
            .refreshAfterWrite(3, TimeUnit.SECONDS)
            .build(cacheLoader);

    public static void main(String[] args) {
        //AtomicInteger counter = new AtomicInteger(0);
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            try {
                long start = System.currentTimeMillis();
                String key = Thread.currentThread().getName();
                String target = loadingCache.get(key);
                System.out.println("key=" + key + " value=" + target + " state=" + Thread.currentThread().getState() + " now=" + System.currentTimeMillis() + " cost=" + (System.currentTimeMillis() - start) + " state:" + loadingCache.stats());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private static class ThreadPoolUtil {
        private static final Executor executor = Executors.newFixedThreadPool(1);
        public static Executor getInstance() {
            return executor;
        }
    }
}
