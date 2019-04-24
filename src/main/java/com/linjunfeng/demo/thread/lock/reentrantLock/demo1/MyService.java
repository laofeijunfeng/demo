package com.linjunfeng.demo.thread.lock.reentrantLock.demo1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
    private Lock lock = new ReentrantLock();    // 获取锁
    public void testMethod() {
        lock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println("ThreadName = " + Thread.currentThread().getName() + " " + (i + 1));
        }
        lock.unlock();  // 释放锁
    }
}
