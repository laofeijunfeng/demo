package com.linjunfeng.demo.math.Lesson6;

import java.util.Arrays;

public class Lesson6_1 {

    public static int[] merge_sort(int[] to_sort) {
        if (to_sort == null)
            return new int[0];

        if (to_sort.length == 1)
            return to_sort;

        int mid = to_sort.length / 2;
        int[] left = Arrays.copyOfRange(to_sort, 0, mid);
        int[] right = Arrays.copyOfRange(to_sort, mid, to_sort.length);

        // 嵌套调用，对两半分别进行排序
        left = merge_sort(left);
        right = merge_sort(right);

        // 合并排序后的两半
        int[] merged = merge(left, right);

        return merged;
    }

    public static int[] merge(int[] a, int[] b) {
        if (a == null)
            a = new int[0];
        if (b == null)
            b = new int[0];

        int[] merged_one = new int[a.length + b.length];

        int mi = 0, ai = 0, bi = 0;

        while (ai < a.length && bi < b.length) {
            if (a[ai] <= b[bi]) {
                merged_one[mi] = a[ai];
                ai ++;
            } else {
                merged_one[mi] = b[bi];
                bi ++;
            }
            mi ++;
        }

        if (ai < a.length) {
            for (int i = ai; i < a.length; i++) {
                merged_one[mi] = a[i];
                mi ++;
            }
        } else {
            for (int i = bi; i < b.length; i++) {
                merged_one[mi] = b[i];
                mi ++;
            }
        }

        return merged_one;
    }

    public static void main(String[] args) {
        int[] to_sort = {3434, 3356, 67, 12334, 878667, 387};
        int[] sorted = merge_sort(to_sort);

        for (int aSorted : sorted)
            System.out.println(aSorted);
    }
}
