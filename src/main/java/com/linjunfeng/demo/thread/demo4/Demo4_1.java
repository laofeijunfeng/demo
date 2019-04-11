package com.linjunfeng.demo.thread.demo4;

public class Demo4_1 extends Thread {

    public Demo4_1() {
        System.out.println("Demo4_1 --- begin");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("this.getName() = " + this.getName());
        System.out.println("Demo4_1 --- end");
    }

    @Override
    public void run() {
        System.out.println("run --- begin");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("this.getName() = " + this.getName());
        System.out.println("run --- end");
    }

    public static void main(String[] args) {
        Demo4_1 demo4_1 = new Demo4_1();
        Thread thread = new Thread(demo4_1);
        thread.setName("A");
        thread.start();
    }
}
