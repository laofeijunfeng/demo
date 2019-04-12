package com.linjunfeng.demo.thread.demo7;

public class Demo7_6 {
    /**
     * 出现异常时，锁自动释放
     */
    synchronized public void testMethod() {
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("ThreadName = " + Thread.currentThread().getName()
                    + " run beginTime = " + System.currentTimeMillis());
            int i = 1;
            while (i == 1) {
                if (("" + Math.random()).substring(0, 8).equals("0.123456")) {
                    System.out.println("ThreadName = "
                            + Thread.currentThread().getName()
                            + " run exceptionTime = "
                            + System.currentTimeMillis());
                    Integer.parseInt("a");
                }
            }
        } else {
            System.out.println("Thread B run Time = " + System.currentTimeMillis());
        }
    }

    public static class ThreadA extends Thread {
        private Demo7_6 demo7_6;

        public ThreadA(Demo7_6 demo7_6) {
            super();
            this.demo7_6 = demo7_6;
        }

        @Override
        public void run() {
            demo7_6.testMethod();
        }
    }

    public static class ThreadB extends Thread {
        private Demo7_6 demo7_6;

        public ThreadB(Demo7_6 demo7_6) {
            super();
            this.demo7_6 = demo7_6;
        }

        @Override
        public void run() {
            demo7_6.testMethod();
        }
    }

    public static void main(String[] args) {
        try {
            Demo7_6 demo7_6 = new Demo7_6();
            ThreadA a = new ThreadA(demo7_6);
            a.setName("a");
            a.start();
            Thread.sleep(500);
            ThreadB b = new ThreadB(demo7_6);
            b.setName("b");
            b.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
