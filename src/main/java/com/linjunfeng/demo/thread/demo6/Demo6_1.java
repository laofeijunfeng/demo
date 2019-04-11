package com.linjunfeng.demo.thread.demo6;

public class Demo6_1 extends Thread {
    private long i = 0;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run() {
        while (true) {
            i ++;
        }
    }

    public static void main(String[] args) {
        try {
            Demo6_1 demo6_1 = new Demo6_1();
            demo6_1.start();
            Thread.sleep(1000);
            demo6_1.suspend();
            System.out.println("A = " + System.currentTimeMillis() + " i = " + demo6_1.getI());
            Thread.sleep(1000);
            System.out.println("A = " + System.currentTimeMillis() + " i = " + demo6_1.getI());

            demo6_1.resume();
            Thread.sleep(1000);

            demo6_1.suspend();
            System.out.println("B = " + System.currentTimeMillis() + " i = " + demo6_1.getI());
            Thread.sleep(1000);
            System.out.println("B = " + System.currentTimeMillis() + " i = " + demo6_1.getI());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
