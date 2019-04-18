package com.linjunfeng.demo.thread.communication.waitAndNotify.demo3;

public class Test {

    /**
     * wait 状态调用 interrupt 则会出现 InterruptedException 异常
     * @param args
     */

    public static void main(String[] args) {
        try {
            Object lock = new Object();
            ThreadA a = new ThreadA(lock);
            a.start();
            Thread.sleep(5000);
            a.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
