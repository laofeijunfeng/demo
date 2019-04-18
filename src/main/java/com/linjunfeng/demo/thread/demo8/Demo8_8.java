package com.linjunfeng.demo.thread.demo8;

public class Demo8_8 extends Thread {

    /**
     * 通过 volatile 关键字使变量在线程间可见
     */

    private volatile boolean isRunning = true;
    public boolean isRunning() {
        return isRunning;
    }
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        System.out.println("进入 run 了");
        while (isRunning) {
            System.out.println(System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("线程被停止了");
    }

    public static void main(String[] args) throws InterruptedException {
        Demo8_8 demo8_8 = new Demo8_8();
        demo8_8.start();
        Thread.sleep(2000);
        demo8_8.setRunning(false);
        System.out.println("已经赋值为 false");
    }
}
