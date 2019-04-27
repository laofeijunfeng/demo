package com.linjunfeng.demo.thread.singleton.demo5;

public class Run {

    /**
     * 静态代码块中的代码在使用类的时候就已经执行了
     * 所以可以应用静态代码块的这个特性来实现单例设计模式
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
