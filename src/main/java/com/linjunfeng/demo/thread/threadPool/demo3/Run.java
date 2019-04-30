package com.linjunfeng.demo.thread.threadPool.demo3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Run {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 15; i++) {
            MyThread myThread = new MyThread(i);
            service.execute(myThread);
        }
        service.shutdown();
    }
}
