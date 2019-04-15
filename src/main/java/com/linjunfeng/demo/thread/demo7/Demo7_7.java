package com.linjunfeng.demo.thread.demo7;

public class Demo7_7 {

    /**
     * 继承不具备同步性，除非在子类也加上 synchronized 关键字
     */

    synchronized public void serviceMethod() {
        try {
            System.out.println("int main 下一步 sleep begin threadName = "
                    + Thread.currentThread().getName() + " time = "
                    + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("int main 下一步 sleep end threadName = "
                    + Thread.currentThread().getName() + " time = "
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class Sub extends Demo7_7 {
        @Override
        public void serviceMethod() {
            try {
                System.out.println("int sub 下一步 sleep begin threadName = "
                        + Thread.currentThread().getName() + " time = "
                        + System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("int sub 下一步 sleep end threadName = "
                        + Thread.currentThread().getName() + " time = "
                        + System.currentTimeMillis());
                super.serviceMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class MyThreadA extends Thread {
        private Sub sub;

        public MyThreadA(Sub sub) {
            super();
            this.sub = sub;
        }

        @Override
        public void run() {
            sub.serviceMethod();
        }
    }

    public static class MyThreadB extends Thread {
        private Sub sub;

        public MyThreadB(Sub sub) {
            super();
            this.sub = sub;
        }

        @Override
        public void run() {
            sub.serviceMethod();
        }
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        MyThreadA a = new MyThreadA(sub);
        a.setName("A");
        a.start();
        MyThreadB b = new MyThreadB(sub);
        b.setName("B");
        b.start();
    }
}
