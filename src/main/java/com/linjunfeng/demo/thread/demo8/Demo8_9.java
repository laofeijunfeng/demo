package com.linjunfeng.demo.thread.demo8;

public class Demo8_9 extends Thread {

    /**
     * 验证 volatile 使变量具有可变性，担不具备同步性和原子性
     */

    volatile public static int count;
    /*private static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println("count = " + count);
    }*/

    /**
     * 修改一下 addCount 方法则可以实现原子性
     */
    synchronized private static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println("count = " + count);
    }

    @Override
    public void run() {
        addCount();
    }

    public static void main(String[] args) {
        Demo8_9[] demo8_9s = new Demo8_9[100];
        for (int i = 0; i < 100; i++) {
            demo8_9s[i] = new Demo8_9();
        }
        for (int i = 0; i < 100; i++) {
            demo8_9s[i].start();
        }
    }
}
