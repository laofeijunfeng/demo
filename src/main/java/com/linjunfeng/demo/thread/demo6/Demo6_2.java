package com.linjunfeng.demo.thread.demo6;

public class Demo6_2 extends Thread {
    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 500000; i++) {
            Thread.yield();
            count = count + (i + 1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("用时：" + (endTime - beginTime) + " 毫秒！");
    }

    public static void main(String[] args) {
        Demo6_2 demo6_2 = new Demo6_2();
        demo6_2.start();
    }
}
