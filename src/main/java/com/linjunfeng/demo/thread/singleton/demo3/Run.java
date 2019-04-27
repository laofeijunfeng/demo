package com.linjunfeng.demo.thread.singleton.demo3;

public class Run {

    /**
     * 使用静态内置类实现单例模式的非线程安全问题
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
