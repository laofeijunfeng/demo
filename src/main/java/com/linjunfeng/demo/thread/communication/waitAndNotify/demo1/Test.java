package com.linjunfeng.demo.thread.communication.waitAndNotify.demo1;

public class Test {

    /**
     * 使用 wait 和 notify 进行线程的等待/通知操作，改操作需要有同步锁，不然抛出异常
     * @param args
     */

    public static void main(String[] args) {
        try {
            Object lock = new Object();
            MyThread1 thread1 = new MyThread1(lock);
            thread1.start();
            Thread.sleep(2000);
            MyThread2 thread2 = new MyThread2(lock);
            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
