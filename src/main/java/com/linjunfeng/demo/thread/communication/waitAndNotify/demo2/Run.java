package com.linjunfeng.demo.thread.communication.waitAndNotify.demo2;

public class Run {

    /**
     * 日志信息 wait end 打印在最后，说明 notify() 方法执行后并不立即释放锁
     * @param args
     */

    public static void main(String[] args) {
        try {
            Object lock = new Object();
            ThreadA a = new ThreadA(lock);
            a.start();
            Thread.sleep(50);
            ThreadB b = new ThreadB(lock);
            b.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
