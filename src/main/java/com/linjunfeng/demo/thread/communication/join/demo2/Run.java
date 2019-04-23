package com.linjunfeng.demo.thread.communication.join.demo2;

public class Run {

    /**
     * join 和 interrupt 如果相遇则会出现异常
     * @param args
     */

    public static void main(String[] args) {
        try {
            ThreadB b = new ThreadB();
            b.start();
            Thread.sleep(500);
            ThreadC c = new ThreadC(b);
            c.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
