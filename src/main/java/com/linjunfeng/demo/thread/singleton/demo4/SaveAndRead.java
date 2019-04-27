package com.linjunfeng.demo.thread.singleton.demo4;

import java.io.*;

public class SaveAndRead {

    /**
     * 静态内置类对象，如果遇到序列化对象时，使用默认方式运行得到的结果还是多例的。
     * 解决的方法是在反序列化中使用 readResolve() 方法。
     * @param args
     */

    public static void main(String[] args) {
        try {
            MyObject myObject = MyObject.getInstance();
            FileOutputStream fosRef = new FileOutputStream(new File("myObjectFile.txt"));
            ObjectOutputStream oosRef = new ObjectOutputStream(fosRef);
            oosRef.writeObject(myObject);
            oosRef.close();
            fosRef.close();
            System.out.println(myObject.hashCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fisRef = new FileInputStream(new File("myObjectFile.txt"));
            ObjectInputStream iosRef = new ObjectInputStream(fisRef);
            MyObject myObject = (MyObject) iosRef.readObject();
            iosRef.close();
            fisRef.close();
            System.out.println(myObject.hashCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
