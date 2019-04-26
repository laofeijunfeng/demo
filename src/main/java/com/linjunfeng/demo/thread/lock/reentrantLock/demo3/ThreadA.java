package com.linjunfeng.demo.thread.lock.reentrantLock.demo3;

public class ThreadA extends Thread {

    private MyService service;

    public ThreadA(MyService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.await();
    }
}
