package com.linjunfeng.demo.gitchat.math.Lession3;

public class Lession3_2 {

    /**
     * 使用二分法计算大于 1 的整数的平方根
     * @param n 待求的数
     * @param deltaThreshold 误差的阀值
     * @param maxTry 二分法的最大次数
     * @return
     */
    private static double getSquareRoot(int n, double deltaThreshold, int maxTry) {
        if (n < 1)
            return -1.0;

        double min = 1.0, max = (double) n;
        for (int i = 0; i < maxTry; i++) {
            double middle = min + (max - min) / 2;  // 不用 (max + min) / 2 为了防止溢出
            double square = middle * middle;
            double delta = Math.abs((square / n) - 1);
            if (delta <= deltaThreshold)
                return middle;
            else {
                if (square > n)
                    max = middle;
                else
                    min = middle;
            }
        }

        return -2.0;
    }

    public static void main(String[] args) {
        int number = 10;
        double squareRoot = getSquareRoot(number, 0.000001, 10000);
        if (squareRoot == -1.0)
            System.out.println("请输入大于 1 的整数");
        else if (squareRoot == -2.0)
            System.out.println("未能找到解");
        else
            System.out.println(String.format("%d 的平方根是 %f", number, squareRoot));
    }
}
