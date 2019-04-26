package com.linjunfeng.demo.thread.lock.reentrantLock.demo2;

public class ThreadA extends Thread {
    private MyService service;

    public ThreadA (MyService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.methodA();
    }
}
