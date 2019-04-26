package com.linjunfeng.demo.thread.lock.reentrantLock.demo3;

public class Run {

    /**
     * 使用 Condition 实现 等待/通知 机制
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.start();
        Thread.sleep(3000);
        service.signal();
    }
}
