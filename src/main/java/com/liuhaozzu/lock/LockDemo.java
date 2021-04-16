package com.liuhaozzu.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteOrder;

/**
 * @author liuhao01
 * @date 1/7/21 2:16 PM
 */
@Slf4j
public class LockDemo {


    @Test
    public void synchronizedTest() throws Exception {
        Thread.sleep(5000);
        Object lock = new Object();
        //System.out.println("hash:" + lock.hashCode());
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

    }

    @Test
    public void nimingPianxiangsuo() throws Exception{
        Thread.sleep(5000);
        Object lock = new Object();
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        synchronized (lock) {
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }
    }

    @Test
    public void qingliangjisuo() throws InterruptedException {
        System.out.println("main:" + getAddresses(Thread.currentThread()));

        Thread.sleep(5000);
        Object o = new Object();
        System.out.println("1"+ClassLayout.parseInstance(o).toPrintable());


        //System.out.println("1"+ClassLayout.parseInstance(o).toPrintable());
        //List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int j=i;
            Thread t = new Thread(() -> {
                print(o,j);
                print(o,j+10);
                print(o,j+20);
                print(o,j+30);


            });
            //list.add(t);
            t.start();
            t=null;
            System.gc();
            Thread.sleep(3000);
            System.gc();
            int m=j;
            while (m++ < 100) {
                System.gc();
            }
        }
        Thread.sleep(3000);
        synchronized (o) {
            System.out.println("2"+ClassLayout.parseInstance(o).toPrintable());
        }

    }

    public void print(Object o,int i) {
        synchronized (o){
            //System.out.println("3:" + i + ":" + Thread.currentThread().getName() + ":" + ClassLayout.parseInstance(o).toPrintable());
            System.out.println("3:" + i + ":" +  ":" + ClassLayout.parseInstance(o).toPrintable());
        }
    }


    @Test
    public void binaryTest() {
        //729864207
        String hash = "0101011100000001101100000001111";
        System.out.println(Integer.parseInt(hash,2));
    }




    @Test
    public void test9() throws Exception{

        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            System.out.println("BIG_ENDIAN");
        } else {
            System.out.println("LITTLE_ENDIAN");
        }

        // 先睡眠5秒，保证开启偏向锁
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) { // -XX:-UseBiasedLocking
            e.printStackTrace(); // -XX:BiasedLockingStartupDelay=0
        }
        Object lock = new Object();
        System.out.println("111"+ClassLayout.parseInstance(lock).toPrintable());
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("222"+ClassLayout.parseInstance(lock).toPrintable());
            }

            System.out.println("222___"+ClassLayout.parseInstance(lock).toPrintable());
        });
        t1.start();
        t1.join(); // 确保t1执行完了再执行当前主线程
        Thread.sleep(1000);
        System.out.println("main: " + t1.getState());

        Thread.sleep(1000);
        new Thread(()->{
            synchronized (lock) {
                System.out.println("333"+ClassLayout.parseInstance(lock).toPrintable());
            }
        }).start();


        Thread.sleep(1000);

        synchronized (lock) {
            System.out.println("333main"+ClassLayout.parseInstance(lock).toPrintable());
        }

        System.out.println("444"+ClassLayout.parseInstance(lock).toPrintable());
    }




    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        System.out.println(ClassLayout.parseInstance(lock));

        //System.out.println(ClassLayout.parseClass(lock).toPrintable());

        //log.debug("hashcode:{}",Integer.toHexString(lock.hashCode()));
        log.debug("blank:::"+ClassLayout.parseInstance(lock).toPrintable());

        Thread t1 = new Thread(()->{
            test();
        });
        Thread t2 = new Thread(()->{
            test();
        });
        t1.start();

        //t1.join(); //打开注释为轻量级锁
        t2.start();

    }

    static void test() {
        log.debug(Thread.currentThread().getName() + "：：" + Thread.currentThread().getId() + "：" + ClassLayout.parseInstance(lock).toPrintable());
        synchronized (lock) {
            try {
                Thread.sleep(1000);
                log.info(Thread.currentThread().getName());
                log.debug(ClassLayout.parseInstance(lock).toPrintable());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void endTest() throws Exception{

        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            System.out.println("BIG_ENDIAN");
        } else {
            System.out.println("LITTLE_ENDIAN");
        }

    }

    @Test
    public void test2() {
        long a = 0x715585d30L;
        System.out.println(a);
        System.out.println(Long.toBinaryString(a));

        System.out.println(Long.parseLong("11111111111010101001100100000001000100000000000", 2));
        System.out.println(Long.parseLong("000000000000000001111111100110111101011010000001010010", 2));

    }


    public static String getAddresses(Object... objects)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("0x");
        // sun.arch.data.model=32 // 32 bit JVM
        // sun.arch.data.model=64 // 64 bit JVM
        boolean is64bit = Integer.parseInt(System.getProperty("sun.arch.data.model")) == 32 ? false : true;
        Unsafe unsafe = getUnsafe();
        long last = 0;
        int offset = unsafe.arrayBaseOffset(objects.getClass());
        int scale = unsafe.arrayIndexScale(objects.getClass());
        switch (scale)
        {
            case 4:
                long factor = is64bit ? 8 : 1;
                final long i1 = (unsafe.getInt(objects, offset) & 0xFFFFFFFFL) * factor;
                // 输出指针地址
                sb.append(Long.toHexString(i1));
                last = i1;
                for (int i = 1; i < objects.length; i++)
                {
                    final long i2 = (unsafe.getInt(objects, offset + i * 4) & 0xFFFFFFFFL) * factor;
                    if (i2 > last)
                        sb.append(", +" + Long.toHexString(i2 - last));
                    else
                        sb.append(", -" + Long.toHexString(last - i2));
                    last = i2;
                }
                break;
            case 8:
                throw new AssertionError("Not supported");
        }
        return sb.toString();
    }
    private static Unsafe getUnsafe()
    {
        try
        {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        }
        catch (Exception e)
        {
            throw new AssertionError(e);
        }
    }
}
