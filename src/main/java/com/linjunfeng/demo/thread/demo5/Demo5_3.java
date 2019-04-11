package com.linjunfeng.demo.thread.demo5;

public class Demo5_3 extends Thread {

    /**
     * 使用 break 停止线程；
     */

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            if (interrupted()) {
                System.out.println("已经是停止状态！我要退出了！");
                break;
            }
            System.out.println("i = " + (i + 1));
        }
        System.out.println("我被输出了，线程并未停止！");
    }

    public static void main(String[] args) {
        try {
            Demo5_3 demo5_3 = new Demo5_3();
            demo5_3.start();
            Thread.sleep(1000);
            demo5_3.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
