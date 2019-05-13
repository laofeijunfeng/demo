package com.linjunfeng.demo.others.demo1;

import java.text.ParseException;
import java.util.Date;

public class Demo1_2 extends Thread {
    @Override
    public void run() {
        try {
            Date date = Demo1_1.prase("2019-01-01 00:00:00");
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            Demo1_2 demo1_2 = new Demo1_2();
            demo1_2.start();
        }
    }
}
