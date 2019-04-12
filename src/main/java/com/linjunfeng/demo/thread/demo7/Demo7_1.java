package com.linjunfeng.demo.thread.demo7;

public class Demo7_1 {
    /**
     * 使用 synchronized 同步，使得实例变量线程安全
     */
    private int num = 0;
    synchronized public void addI(String username) {
        try {
            if (username.equals("a")) {
                num = 100;
                System.out.println("a set over!");
                Thread.sleep(2000);
            } else {
                num = 200;
                System.out.println("b set over!");
            }
            System.out.println(username + " num = " + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class ThreadA extends Thread {
        private Demo7_1 demo7_1;
        public ThreadA(Demo7_1 demo7_1) {
            super();
            this.demo7_1 = demo7_1;
        }

        @Override
        public void run() {
            super.run();
            demo7_1.addI("a");
        }
    }

    private static class ThreadB extends Thread {
        private Demo7_1 demo7_1;
        public ThreadB(Demo7_1 demo7_1) {
            super();
            this.demo7_1 = demo7_1;
        }

        @Override
        public void run() {
            super.run();
            demo7_1.addI("b");
        }
    }

    /*public static void main(String[] args) {
        Demo7_1 demo7_1 = new Demo7_1();
        ThreadA threadA = new ThreadA(demo7_1);
        threadA.start();
        ThreadB threadB = new ThreadB(demo7_1);
        threadB.start();
    }*/
    public static void main(String[] args) {
        /**
         * 多个对象多个锁
         */
        Demo7_1 demo7_1_1 = new Demo7_1();
        Demo7_1 demo7_1_2 = new Demo7_1();
        ThreadA threadA = new ThreadA(demo7_1_1);
        threadA.start();
        ThreadB threadB = new ThreadB(demo7_1_2);
        threadB.start();
    }
}
