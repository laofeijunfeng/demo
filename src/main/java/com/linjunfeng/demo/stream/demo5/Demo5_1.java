package com.linjunfeng.demo.stream.demo5;

import com.linjunfeng.demo.stream.utils.ToolUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Demo5_1 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("a", "bb", "cccc", "ddd", "eeeee");

        Stream<String> uniqueWords = Stream.of("merrily", "merrily", "gently").distinct();
        ToolUtil.show("distinct 的使用结果：", uniqueWords);

        Stream<String> longestFirst = words.stream().sorted(Comparator.comparing(String::length).reversed());
        ToolUtil.show("sorted 的使用结果：", longestFirst);

        Stream<String> peekWords = words.stream().peek(w -> System.out.println("都会调用一次：" + w));
        ToolUtil.show("peek 的使用结果：", peekWords);
    }
}
