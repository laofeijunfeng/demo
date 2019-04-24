package com.linjunfeng.demo.thread.communication.threadLocal.demo2;

public class Run {

    /**
     * 解决 get 返回 null 的问题
     */

    public static ThreadLocalExt t1 = new ThreadLocalExt();
    public static void main(String[] args) {
        if (t1.get() == null) {
            System.out.println("从未放过值");
            t1.set("我的值");
        }
        System.out.println(t1.get());
        System.out.println(t1.get());
    }
}
