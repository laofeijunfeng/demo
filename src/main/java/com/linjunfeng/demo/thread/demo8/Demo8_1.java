package com.linjunfeng.demo.thread.demo8;

public class Demo8_1 {

    /**
     * 一半的异步，一半的同步
     */

    public void doLongTimeTask() {
        for (int i = 0; i < 100; i++) {
            System.out.println("nosynchronized threadName = " + Thread.currentThread().getName() + " i = " + (i + 1));
        }
        System.out.println();
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println("synchronized threadName = " + Thread.currentThread().getName() + " i = " + (i + 1));
            }
        }
    }

    public static class MyThread1 extends Thread {
        private Demo8_1 demo8_1;

        public MyThread1(Demo8_1 demo8_1) {
            super();
            this.demo8_1 = demo8_1;
        }

        @Override
        public void run() {
            super.run();
            demo8_1.doLongTimeTask();
        }
    }

    public static class MyThread2 extends Thread {
        private Demo8_1 demo8_1;

        public MyThread2(Demo8_1 demo8_1) {
            super();
            this.demo8_1 = demo8_1;
        }

        @Override
        public void run() {
            super.run();
            demo8_1.doLongTimeTask();
        }
    }

    public static void main(String[] args) {
        Demo8_1 demo8_1 = new Demo8_1();
        MyThread1 thread1 = new MyThread1(demo8_1);
        thread1.start();
        MyThread2 thread2 = new MyThread2(demo8_1);
        thread2.start();
    }
}
