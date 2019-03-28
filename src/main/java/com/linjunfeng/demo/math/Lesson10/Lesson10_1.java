package com.linjunfeng.demo.math.Lesson10;

public class Lesson10_1 {
    public static int getStrDistance(String a, String b) {
        if (a == null || b == null)
            return -1;

        // 初始用于记录化转移的二维表
        int[][] d = new int[a.length() + 1][b.length() + 1];

        // 如果 i 为 0，且 j 大于等于 0，那么 d[i, j] 为 j
        for (int j = 0; j <= b.length(); j++)
            d[0][j] = j;

        // 如果 i 大于等于 0，且 j 为 0，那么 d[i, j] 为 i
        for (int i = 0; i <= a.length(); i++)
            d[i][0] = i;

        // 实现转移方程
        // d[i, j]=min(d[i-1, j] + 1, d[i, j-1] + 1, d[i-1, j-1] + r(i, j))
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                int r = 0;
                if (a.charAt(i) != b.charAt(j))
                    r = 1;

                int first_append = d[i][j + 1] + 1;
                int second_append = d[i + 1][j] + 1;
                int replace = d[i][j] + r;

                int min = Math.min(first_append, second_append);
                min = Math.min(min, replace);
                d[i + 1][j + 1] = min;
            }
        }
        return d[a.length()][b.length()];
    }

    public static void main(String[] args) {
        System.out.println(getStrDistance("mouse", "mouuse"));
    }
}
