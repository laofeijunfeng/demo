package com.linjunfeng.demo.thread.demo8;

import java.util.ArrayList;
import java.util.List;

public class Demo8_4 {
    /**
     * 验证出现了脏读，两个线程以异步的方式返回 list 参数的 size() 大小。
     */
    private List list = new ArrayList();
    synchronized public void add(String data) {
        list.add(data);
    }
    synchronized public int getSize() {
        return list.size();
    }

    /*public static class MyService {
        public Demo8_4 addServiceMethod(Demo8_4 demo8_4, String data) {
            try {
                if (demo8_4.getSize() < 1) {
                    Thread.sleep(2000);
                    demo8_4.add(data);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return demo8_4;
        }
    }*/

    /**
     * 解决的方法是使用同步
     */
    public static class MyService {
        public Demo8_4 addServiceMethod(Demo8_4 demo8_4, String data) {
            try {
                synchronized (demo8_4) {
                    if (demo8_4.getSize() < 1) {
                        Thread.sleep(2000);
                        demo8_4.add(data);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return demo8_4;
        }
    }

    public static class MyThread1 extends Thread {
        private Demo8_4 demo8_4;
        public MyThread1(Demo8_4 demo8_4) {
            super();
            this.demo8_4 = demo8_4;
        }

        @Override
        public void run() {
            MyService myService = new MyService();
            myService.addServiceMethod(demo8_4, "A");
        }
    }

    public static class MyThread2 extends Thread {
        private Demo8_4 demo8_4;
        public MyThread2(Demo8_4 demo8_4) {
            super();
            this.demo8_4 = demo8_4;
        }

        @Override
        public void run() {
            MyService myService = new MyService();
            myService.addServiceMethod(demo8_4, "B");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo8_4 demo8_4 = new Demo8_4();
        MyThread1 thread1 = new MyThread1(demo8_4);
        thread1.setName("A");
        thread1.start();
        MyThread2 thread2 = new MyThread2(demo8_4);
        thread2.setName("B");
        thread2.start();
        Thread.sleep(6000);
        System.out.println("ListSize = " + demo8_4.getSize());
    }
}
