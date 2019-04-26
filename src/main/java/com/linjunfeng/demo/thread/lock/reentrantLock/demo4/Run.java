package com.linjunfeng.demo.thread.lock.reentrantLock.demo4;

public class Run {

    /**
     * 通过分组的方式实现通知部分线程
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();
        Thread.sleep(3000);
        service.signalAll_A();
    }
}
