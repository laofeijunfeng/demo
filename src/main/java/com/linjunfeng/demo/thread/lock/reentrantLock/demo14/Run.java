package com.linjunfeng.demo.thread.lock.reentrantLock.demo14;

public class Run {
    public static void main(String[] args) {
        final Service service = new Service(true);
        Runnable runnable = service::serviceMethod;
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
