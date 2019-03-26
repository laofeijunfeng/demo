package com.linjunfeng.demo.math.Lesson5;

import java.util.ArrayList;

public class Lesson5_1 {

    public static long[] rewards = {1, 2, 5, 10};

    /**
     * 使用函数递归（嵌套）调用，找出所有可能的加起来结果为 10 的组合
     * @param totalReward 奖赏总金额
     * @param result
     */
    public static void get(long totalReward, ArrayList<Long> result) {
        if (totalReward == 0) {
            System.out.println(result);
        } else if (totalReward > 0) {
            for (long reward : rewards) {
                ArrayList<Long> newResult = (ArrayList<Long>) (result.clone());
                newResult.add(reward);
                get(totalReward - reward, newResult);
            }
        }
    }

    public static void main(String[] args) {
        int totalReward = 10;
        get(totalReward, new ArrayList<>());
    }
}
