package com.linjunfeng.demo.thread.singleton.demo4;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class MyObject implements Serializable {
    private static final long serialVersionUid = 888L;
    // 内部类方式
    private static class MyObjectHandler {
        private static final MyObject myObject = new MyObject();
    }
    private MyObject() { }
    public static MyObject getInstance() {
        return MyObjectHandler.myObject;
    }
    protected Object readResolve() throws ObjectStreamException {
        System.out.println("调用了 readResolve 方法");
        return MyObjectHandler.myObject;
    }
}
