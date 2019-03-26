package com.linjunfeng.demo.math.Lesson7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Lesson7_1 {

    // 设置齐王的马跑完所需的时间
    public static HashMap<String, Double> q_hourse_time = new HashMap<String, Double>(){
        {
            put("q1", 1.0);
            put("q2", 2.0);
            put("q3", 3.0);
        }
    };

    // 设置田忌的马跑完所需的时间
    public static HashMap<String, Double> t_hourse_time = new HashMap<String, Double>() {
        {
            put("t1", 1.5);
            put("t2", 2.5);
            put("t3", 3.5);
        }
    };

    public static ArrayList<String> q_horses = new ArrayList<>(Arrays.asList("q1", "q2", "q3"));

    /**
     * 使用函数的递归（嵌套）调用，找出所有可能的马匹出场顺序
     * @param horses 目前还剩多少马没出战
     * @param result 保存当前已经出战的马匹及顺序
     */
    public static void permutate(ArrayList<String> horses, ArrayList<String>result) {
        if (horses.size() == 0) {
            System.out.println(result);
            compare(result, q_horses);
            System.out.println();
            return;
        }

        for (String horse : horses) {
            // 从未出战的马匹中旋选择一匹，加入结果
            ArrayList<String> new_result = (ArrayList<String>) (result.clone());
            new_result.add(horse);
            // 将已选择的马匹从未出战的列表中移出
            ArrayList<String> rest_horses = (ArrayList<String>) (horses.clone());
            rest_horses.remove(horse);
            // 递归调用，对剩余的马匹急需生成排列
            permutate(rest_horses, new_result);
        }
    }

    public static void compare(ArrayList<String> t, ArrayList<String> q) {
        int t_won_cnt = 0;
        for (int i = 0; i < t.size(); i++) {
            System.out.println(t_hourse_time.get(t.get(i)) + " " + q_hourse_time.get(q.get(i)));
            if (t_hourse_time.get(t.get(i)) < q_hourse_time.get(q.get(i)))
                t_won_cnt ++;
        }
        if (t_won_cnt > (t.size() / 2))
            System.out.println("田忌获胜！");
        else
            System.out.println("齐王获胜！");
    }

    public static void main(String[] args) {
        ArrayList<String> horses = new ArrayList<>(Arrays.asList("t1", "t2", "t3"));
        permutate(horses, new ArrayList<>());
    }
}
