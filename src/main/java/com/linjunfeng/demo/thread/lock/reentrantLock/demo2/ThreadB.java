package com.linjunfeng.demo.thread.lock.reentrantLock.demo2;

public class ThreadB extends Thread {
    private MyService service;

    public ThreadB (MyService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.methodB();
    }
}
