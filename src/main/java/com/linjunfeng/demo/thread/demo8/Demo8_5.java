package com.linjunfeng.demo.thread.demo8;

public class Demo8_5 {

    /**
     * 从结果可知道异步的原因是持有不同的锁，一个是对象锁，一个是Class锁，Class锁可以对类的所有对象实例作用。
     */

    synchronized public static void printA() {
        try {
            System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 进入 printA");
            Thread.sleep(3000);
            System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 离开 printB");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public static void printB() {
        System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 进入 printB");
        System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 离开 printB");
    }

    synchronized public void printC() {
        System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 进入 printC");
        System.out.println("线程名称为：" + Thread.currentThread().getName() + " 在 " + System.currentTimeMillis() + " 离开 printC");
    }

    public static class ThreadA extends Thread {
        private Demo8_5 demo8_5;
        public ThreadA(Demo8_5 demo8_5) {
            super();
            this.demo8_5 = demo8_5;
        }

        @Override
        public void run() {
            demo8_5.printA();
        }
    }

    public static class ThreadB extends Thread {
        private Demo8_5 demo8_5;
        public ThreadB(Demo8_5 demo8_5) {
            super();
            this.demo8_5 = demo8_5;
        }

        @Override
        public void run() {
            demo8_5.printB();
        }
    }

    public static class ThreadC extends Thread {
        private Demo8_5 demo8_5;
        public ThreadC(Demo8_5 demo8_5) {
            super();
            this.demo8_5 = demo8_5;
        }

        @Override
        public void run() {
            demo8_5.printC();
        }
    }

    public static void main(String[] args) {
        Demo8_5 demo8_5 = new Demo8_5();
        ThreadA a = new ThreadA(demo8_5);
        a.setName("A");
        a.start();
        ThreadB b = new ThreadB(demo8_5);
        b.setName("B");
        b.start();
        ThreadC c = new ThreadC(demo8_5);
        c.setName("C");
        c.start();
    }
}
