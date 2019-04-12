package com.linjunfeng.demo.thread.demo7;

public class Demo7_4 {

    /**
     * 可重入锁
     */

    synchronized public void service1() {
        System.out.println("service1");
        service2();
    }

    synchronized public void service2() {
        System.out.println("service2");
        service3();
    }

    synchronized public void service3() {
        System.out.println("service3");
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            Demo7_4 demo7_4 = new Demo7_4();
            demo7_4.service1();
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
