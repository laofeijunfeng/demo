package com.linjunfeng.demo.others.demo1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo1_1 {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /*public static Date prase (String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(strDate);
    }*/

    public static Date prase (String strDate) throws ParseException {
        synchronized (sdf) {
            return sdf.parse(strDate);
        }
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
    }
}
