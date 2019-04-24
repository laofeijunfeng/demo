package com.linjunfeng.demo.thread.communication.threadLocal.demo2;

public class ThreadLocalExt extends ThreadLocal {
    @Override
    protected Object initialValue() {
        return "默认值，防止第一个 get 不再返回 null";
    }
}
