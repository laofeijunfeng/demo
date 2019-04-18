package com.linjunfeng.demo.thread.demo8;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo8_10 extends Thread {

    /**
     * 测试使用 AtomicInteger 保证原子性操作
     */

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(count.incrementAndGet());
        }
    }

    public static void main(String[] args) {
        Demo8_10 demo8_10 = new Demo8_10();
        Thread t1 = new Thread(demo8_10);
        t1.start();
        Thread t2 = new Thread(demo8_10);
        t2.start();
        Thread t3 = new Thread(demo8_10);
        t3.start();
        Thread t4 = new Thread(demo8_10);
        t4.start();
        Thread t5 = new Thread(demo8_10);
        t5.start();
    }
}
