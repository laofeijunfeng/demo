package com.linjunfeng.demo.thread.communication.waitAndNotify.demo4;

public class Test {

    /**
     * 使用 notifyAll 通知所有线程
     * @param args
     */

    public static void main(String[] args) {
        Object lock = new Object();
        ThreadA a = new ThreadA(lock);
        a.start();
        ThreadB b = new ThreadB(lock);
        b.start();
        ThreadC c = new ThreadC(lock);
        c.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        NotifyThread notifyThread = new NotifyThread(lock);
        notifyThread.start();
    }
}
