package com.linjunfeng.demo.thread.communication.join.demo1;

public class Test {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当 myThread 对象执行完毕之后，再执行");
    }
}
