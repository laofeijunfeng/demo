package com.linjunfeng.demo.thread.lock.reentrantLock.demo2;

public class ThreadBB extends Thread {
    private MyService service;

    public ThreadBB (MyService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.methodB();
    }
}
