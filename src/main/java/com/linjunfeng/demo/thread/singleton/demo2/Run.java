package com.linjunfeng.demo.thread.singleton.demo2;

public class Run {

    /**
     * 缺点是很可能创建出多个对象，并不是单例模式
     * 而使用 DCL 双检查锁机制则可以实现单例模式
     * @param args
     */

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        t1.start();
        t2.start();
        t3.start();
    }
}
