package com.linjunfeng.demo.thread.lock.reentrantLock.demo16;

public class Run {
    public static void main(String[] args) {
        final MyService service = new MyService();
        Runnable runnable = service::waitMethod;
        Thread threadA = new Thread(runnable);
        threadA.setName("A");
        threadA.start();
        Thread threadB = new Thread(runnable);
        threadB.setName("B");
        threadB.start();
    }
}
