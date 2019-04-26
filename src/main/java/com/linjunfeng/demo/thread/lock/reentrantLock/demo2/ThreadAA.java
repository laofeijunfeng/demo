package com.linjunfeng.demo.thread.lock.reentrantLock.demo2;

public class ThreadAA extends Thread {
    private MyService service;

    public ThreadAA (MyService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.methodA();
    }
}
