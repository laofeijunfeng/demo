package com.linjunfeng.demo.thread.demo7;

public class Demo7_2 {

    /**
     * 通过 synchronized 的方法则会变成同步的
     */

    synchronized public void methodA() {
        try {
            System.out.println("begin methodA threadName = " + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class ThreadA extends Thread {
        public Demo7_2 demo7_2;
        public ThreadA(Demo7_2 demo7_2) {
            super();
            this.demo7_2 = demo7_2;
        }

        @Override
        public void run() {
            super.run();
            demo7_2.methodA();
        }
    }

    public static class ThreadB extends Thread {
        public Demo7_2 demo7_2;
        public ThreadB(Demo7_2 demo7_2) {
            super();
            this.demo7_2 = demo7_2;
        }

        @Override
        public void run() {
            super.run();
            demo7_2.methodA();
        }
    }

    public static void main(String[] args) {
        Demo7_2 demo7_2 = new Demo7_2();
        ThreadA a = new ThreadA(demo7_2);
        a.setName("A");
        ThreadB b = new ThreadB(demo7_2);
        b.setName("B");
        a.start();
        b.start();
    }
}
