package com.linjunfeng.demo.thread.demo8;

public class Demo8_2 {

    /**
     * 对象监视器使用同一个的话，则当一个线程访问 object 的一个同步代码块时，其他线程对同一个 object 中所有其他同步代码块的访问将被阻塞
     */

    public void serviceMethodA() {
        try {
            synchronized (this) {
                System.out.println("A begin time = " + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("A begin time = " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void serviceMethodB() {
        synchronized (this) {
            System.out.println("B begin time = " + System.currentTimeMillis());
            System.out.println("B end time = " + System.currentTimeMillis());
        }
    }

    public static class ThreadA extends Thread {
        private Demo8_2 demo8_2;
        public ThreadA(Demo8_2 demo8_2) {
            super();
            this.demo8_2 = demo8_2;
        }

        @Override
        public void run() {
            super.run();
            demo8_2.serviceMethodA();
        }
    }

    public static class ThreadB extends Thread {
        private Demo8_2 demo8_2;
        public ThreadB(Demo8_2 demo8_2) {
            super();
            this.demo8_2 = demo8_2;
        }

        @Override
        public void run() {
            super.run();
            demo8_2.serviceMethodB();
        }
    }

    public static void main(String[] args) {
        Demo8_2 demo8_2 = new Demo8_2();
        ThreadA a = new ThreadA(demo8_2);
        a.setName("a");
        a.start();
        ThreadB b = new ThreadB(demo8_2);
        b.setName("b");
        b.start();
    }
}
