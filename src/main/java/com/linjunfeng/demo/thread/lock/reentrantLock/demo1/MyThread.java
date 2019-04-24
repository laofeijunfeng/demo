package com.linjunfeng.demo.thread.lock.reentrantLock.demo1;

public class MyThread extends Thread {
    private MyService myService;
    public MyThread(MyService myService) {
        super();
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }
}
