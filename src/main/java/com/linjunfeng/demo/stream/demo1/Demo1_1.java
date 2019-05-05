package com.linjunfeng.demo.stream.demo1;

import java.util.Arrays;
import java.util.List;

public class Demo1_1 {
    public static void main(String[] args)  {
        List<String> words = Arrays.asList("aaa", "bbb", "ccc", "dd");
        long count = 0;
        for (String w : words) {
            if (w.length() > 2)
                count++;
        }
        System.out.println("通过遍历数组得到的 count = " + count);

        count = words.stream().filter(w -> w.length() > 2).count();
        System.out.println("通过流操作得到的 count = " + count);
    }
}
