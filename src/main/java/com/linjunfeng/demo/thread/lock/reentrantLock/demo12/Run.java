package com.linjunfeng.demo.thread.lock.reentrantLock.demo12;

public class Run {
    public static void main(String[] args) {
        final Service service1 = new Service(true);
        Runnable runnable = service1::serviceMethod;
        Thread thread = new Thread(runnable);
        thread.start();
        final Service service2 = new Service(false);
        runnable = service2::serviceMethod;
        thread = new Thread(runnable);
        thread.start();
    }
}
