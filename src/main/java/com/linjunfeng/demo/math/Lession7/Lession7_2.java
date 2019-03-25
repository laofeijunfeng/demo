package com.linjunfeng.demo.math.Lession7;

import java.util.ArrayList;
import java.util.Arrays;

public class Lession7_2 {
    public static ArrayList<String> copeOfPassword = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));

    public static void getPassword(ArrayList<String> code, ArrayList<String> result) {
        if (result.size() == 4) {
            compare(result);
            return;
        }

        for (String item : copeOfPassword) {
            ArrayList<String> newResult = (ArrayList<String>)(result.clone());
            newResult.add(item);
            ArrayList<String> newCode = (ArrayList<String>)(code.clone());
            newCode.remove(item);
            getPassword(newCode, newResult);
        }
    }

    public static void compare(ArrayList<String> code) {
        String password = "eead";
        if (String.join("", code).equals(password))
            System.out.println("密码为：" + code.toString());
    }

    public static void main(String[] args) {
        getPassword(copeOfPassword, new ArrayList<>());
    }
}
