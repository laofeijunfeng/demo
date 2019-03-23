package com.linjunfeng.demo.math.Lesson1;

import java.math.BigInteger;

public class Lesson1_1 {

    /**
     * 十进制转二进制
     * @param decimalSource
     * @return
     */
    public static String decimalToBinary(int decimalSource) {
        // 转换成 BigInteger 类型，默认是十进制
        BigInteger bi = new BigInteger(String.valueOf(decimalSource));
        // 参数 2 指定的是转化为二进制
        return bi.toString(2);
    }

    /**
     * 二进制转十进制
     * @param binaryString
     * @return
     */
    public static int binaryToDecimal(String binaryString) {
        // 转换为 BigInteger 类型，参数 2 指的是二进制
        BigInteger bi = new BigInteger(binaryString, 2);
        return Integer.parseInt(bi.toString());
    }

    public static void main(String[] args) {
        int a = 53;
        String b = "110101";
        System.out.println(String.format("数字 %d 的二进制是 %s", a, decimalToBinary(a)));
        System.out.println(String.format("数字 %s 的十进制是 %d", b, binaryToDecimal(b)));
    }
}
