package com.linjunfeng.demo.thread.lock.reentrantLock.demo3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
    private Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    public void await() {
        try {
            lock.lock();    //需要先调用 lock.lock() 获得对象监视器
            System.out.println("await 时间为：" + System.currentTimeMillis());
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void signal() {
        try {
            lock.lock();
            System.out.println("signal 时间为：" + System.currentTimeMillis());
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
