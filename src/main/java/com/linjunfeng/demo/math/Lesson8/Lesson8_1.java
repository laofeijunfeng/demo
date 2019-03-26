package com.linjunfeng.demo.math.Lesson8;

import java.util.ArrayList;
import java.util.Arrays;

public class Lesson8_1 {
    public static void combine(ArrayList<String> teams, ArrayList<String> result, int m) {
        if (result.size() == m) {
            System.out.println(result);
            return;
        }

        for (int i = 0; i < teams.size(); i++) {
            // 从剩下的队伍中，选择一队加入结果
            ArrayList<String> newResult = (ArrayList<String>)(result.clone());
            newResult.add(teams.get(i));

            // 只考虑当前选择之后的所有队伍
            ArrayList<String> rest_teams = new ArrayList<>(teams.subList(i + 1, teams.size()));

            // 递归调用，对于剩下的队伍急需生成组合
            combine(rest_teams, newResult, m);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> teams = new ArrayList<>(Arrays.asList("t1", "t2", "t3"));
        combine(teams, new ArrayList<>(), 2);
    }
}
