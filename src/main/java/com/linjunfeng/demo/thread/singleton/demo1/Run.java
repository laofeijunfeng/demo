package com.linjunfeng.demo.thread.singleton.demo1;

public class Run {

    /**
     * 打印结果可以看到 hashCode 是同一个值
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
