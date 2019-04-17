package com.linjunfeng.demo.thread.demo8;

public class Demo8_3 {

    /**
     * 验证同步 synchronized(this) 代码块是锁定当前对象的
     */

    /*public void otherMethod() {
        System.out.println("------------------------- run -- otherMethod");
    }*/
    synchronized public void otherMethod() {
        System.out.println("------------------------- run -- otherMethod");
    }
    public void doLongTimeTask() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println("synchronized threadName = " + Thread.currentThread().getName() + " i = " + (i + 1));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class MyThread1 extends Thread {
        private Demo8_3 demo8_3;
        public MyThread1(Demo8_3 demo8_3) {
            super();
            this.demo8_3 = demo8_3;
        }

        @Override
        public void run() {
            super.run();
            demo8_3.doLongTimeTask();
        }
    }

    public static class MyThread2 extends Thread {
        private Demo8_3 demo8_3;
        public MyThread2(Demo8_3 demo8_3) {
            super();
            this.demo8_3 = demo8_3;
        }

        @Override
        public void run() {
            super.run();
            demo8_3.otherMethod();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo8_3 demo8_3 = new Demo8_3();
        MyThread1 thread1 = new MyThread1(demo8_3);
        thread1.start();
        Thread.sleep(100);
        MyThread2 thread2 = new MyThread2(demo8_3);
        thread2.start();
    }
}
