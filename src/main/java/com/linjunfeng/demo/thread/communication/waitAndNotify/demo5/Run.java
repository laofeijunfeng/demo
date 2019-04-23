package com.linjunfeng.demo.thread.communication.waitAndNotify.demo5;

public class Run {

    /**
     * 一个生产者对应一个消费者，set 和 get 交替使用
     * @param args
     */

    public static void main(String[] args) {
        String lock = "";
        P p = new P(lock);
        C c = new C(lock);
        ThreadP threadP = new ThreadP(p);
        ThreadC threadC = new ThreadC(c);
        threadP.start();
        threadC.start();
    }
}
