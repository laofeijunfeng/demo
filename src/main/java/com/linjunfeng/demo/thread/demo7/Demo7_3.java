package com.linjunfeng.demo.thread.demo7;

public class Demo7_3 {
    synchronized public void methodA() {
        try {
            System.out.println("begin methodA threadName = " + Thread.currentThread().getName()
                    + "begin time = " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("end endTime = " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void methodB() {
        try {
            System.out.println("begin methodB threadName = " + Thread.currentThread().getName()
                    + "begin time = " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("end endTime = " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class ThreadA extends Thread {
        public Demo7_3 demo7_3;
        public ThreadA(Demo7_3 demo7_3) {
            super();
            this.demo7_3 = demo7_3;
        }

        @Override
        public void run() {
            super.run();
            demo7_3.methodA();
        }
    }

    public static class ThreadB extends Thread {
        public Demo7_3 demo7_3;
        public ThreadB(Demo7_3 demo7_3) {
            super();
            this.demo7_3 = demo7_3;
        }

        @Override
        public void run() {
            super.run();
            demo7_3.methodB();
        }
    }

    public static void main(String[] args) {
        Demo7_3 demo7_3 = new Demo7_3();
        ThreadA a = new ThreadA(demo7_3);
        a.setName("A");
        ThreadB b = new ThreadB(demo7_3);
        b.setName("B");
        a.start();
        b.start();
    }
}
