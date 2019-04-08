package com.linjunfeng.demo.thread.demo3;

public class Demo3_2 extends Thread {
    private int count = 5;

    @Override
    synchronized public void run() {
        super.run();
        count --;
        System.out.println("由 " + currentThread().getName() + " 计算，count = " + count);
    }

    public static void main(String[] args) {
        Demo3_2 demo3_2 = new Demo3_2();
        Thread a = new Thread(demo3_2, "A");
        Thread b = new Thread(demo3_2, "B");
        Thread c = new Thread(demo3_2, "C");
        Thread d = new Thread(demo3_2, "D");
        Thread e = new Thread(demo3_2, "E");
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}
