package com.linjunfeng.demo.math.Lesson1;

public class Lesson1_2 {

    /**
     * 向左移位
     * @param num 等待移位的十进制数
     * @param m 向左移的位数
     * @return
     */
    public static int leftShift(int num, int m) {
        return num << m;
    }

    /**
     * 向右移位
     * @param num 等待移位的十进制数
     * @param m 向右移位的位数
     * @return
     */
    public static int rightShift(int num, int m) {
        return num >>> m;
    }

    public static void main(String[] args) {
        int num = 53;
        int m = 1;
        System.out.println(String.format("数字 %d 的二进制向左移 %d 位是 %d", num, m, leftShift(num, m)));
        System.out.println(String.format("数字 %d 的二进制向右移 %d 位是 %d", num, m, rightShift(num, m)));

        System.out.println();

        m = 3;
        System.out.println(String.format("数字 %d 的二进制向左移 %d 位是 %d", num, m, leftShift(num, m)));
        System.out.println(String.format("数字 %d 的二进制向右移 %d 位是 %d", num, m, rightShift(num, m)));
    }
}
