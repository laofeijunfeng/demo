package com.linjunfeng.demo.thread.communication.inheritableThreadLocal.demo1;

public class Run {

    /**
     * 值的继承
     * @param args
     */

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("在 Main 线程中取值 = " + Tools.t1.get());
                Thread.sleep(100);
            }
            Thread.sleep(5000);
            ThreadA a = new ThreadA();
            a.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
