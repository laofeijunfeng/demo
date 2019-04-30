package com.linjunfeng.demo.thread.threadPool.demo2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Run {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 15; i++) {
            MyThread myThread = new MyThread(i);
            pool.execute(myThread);
        }
        pool.shutdown();
    }
}
