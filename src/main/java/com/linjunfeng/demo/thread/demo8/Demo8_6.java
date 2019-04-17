package com.linjunfeng.demo.thread.demo8;

public class Demo8_6 {

    /**
     * 内置类与同步，因为持有不同的对象监视器而呈现异步执行
     */

    static class Inner {
        public void method1() {
            synchronized ("其他的锁") {
                for (int i = 1; i <= 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " i = " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public synchronized void method2() {
            for (int i = 11; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + " i = " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        final Inner inner = new Inner();
        Thread t1 = new Thread(inner::method1);
        Thread t2 = new Thread(inner::method2);
        t1.start();
        t2.start();
    }
}
