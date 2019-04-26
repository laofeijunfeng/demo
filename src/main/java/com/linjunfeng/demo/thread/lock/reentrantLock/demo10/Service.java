package com.linjunfeng.demo.thread.lock.reentrantLock.demo10;

import java.util.concurrent.locks.ReentrantLock;

public class Service {
    public ReentrantLock lock = new ReentrantLock();
    public void waitMethod() {
        try {
            lock.lock();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
