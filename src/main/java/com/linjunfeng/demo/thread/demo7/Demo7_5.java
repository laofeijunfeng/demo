package com.linjunfeng.demo.thread.demo7;

public class Demo7_5 {

    /**
     * 类继承的锁重入
     */

    public int i = 10;
    synchronized public void operateIMainMethod() {
        try {
            i--;
            System.out.println("main print i = " + i);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class Sub extends Demo7_5 {
        synchronized public void operateISubMethod() {
            try {
                while (i > 0) {
                    i--;
                    System.out.println("sub print i = " + i);
                    Thread.sleep(100);
                    this.operateIMainMethod();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            Sub sub = new Sub();
            sub.operateISubMethod();
        }
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
    }
}
