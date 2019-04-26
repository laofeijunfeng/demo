package com.linjunfeng.demo.thread.lock.reentrantLock.demo5;

public class Run {

    /**
     * 实现 生产者/消费者模式：一对一交替打印
     * @param args
     */

    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThreadA a = new MyThreadA(myService);
        a.start();
        MyThreadB b = new MyThreadB(myService);
        b.start();
    }
}
