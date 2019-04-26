package com.linjunfeng.demo.thread.lock.reentrantReadWriteLock.demo4;

public class Run {
    public static void main(String[] args) {
        Service service = new Service();
        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
    }
}
