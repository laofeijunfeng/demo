package com.linjunfeng.demo.thread.demo8;

public class Demo8_11 {

    /**
     * synchronized 关键字也可保证可见性
     */

    private boolean isContinueRun = true;
    public void runMethod() {
        String anyString = new String();
        while (isContinueRun) {
            synchronized (anyString) {

            }
        }
        System.out.println("停下来了！");
    }
    public void stopMethod() {
        isContinueRun = false;
    }

    public static class ThreadA extends Thread {
        private Demo8_11 demo8_11;
        public ThreadA(Demo8_11 demo8_11) {
            super();
            this.demo8_11 = demo8_11;
        }

        @Override
        public void run() {
            demo8_11.runMethod();
        }
    }

    public static class ThreadB extends Thread {
        private Demo8_11 demo8_11;
        public ThreadB(Demo8_11 demo8_11) {
            super();
            this.demo8_11 = demo8_11;
        }

        @Override
        public void run() {
            demo8_11.stopMethod();
        }
    }

    public static void main(String[] args) {
        Demo8_11 demo8_11 = new Demo8_11();
        ThreadA a = new ThreadA(demo8_11);
        a.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadB b = new ThreadB(demo8_11);
        b.start();
        System.out.println("已经发起了停止的命令");
    }
}
