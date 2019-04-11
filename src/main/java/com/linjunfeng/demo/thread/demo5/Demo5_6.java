package com.linjunfeng.demo.thread.demo5;

public class Demo5_6 extends Thread {

    /**
     * 使用 return 停止线程
     */

    @Override
    public void run() {
        while (true) {
            if (this.isInterrupted()) {
                System.out.println("停止了！");
                return;
            }
            System.out.println("timer = " + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo5_6 demo5_6 = new Demo5_6();
        demo5_6.start();
        Thread.sleep(2000);
        demo5_6.interrupt();
    }
}
