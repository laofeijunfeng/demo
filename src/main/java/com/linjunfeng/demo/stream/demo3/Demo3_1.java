package com.linjunfeng.demo.stream.demo3;

import com.linjunfeng.demo.stream.utils.ToolUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo3_1 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("aaa", "bbb", "ccc", "dd");
        Stream<String> longWords = words.stream().filter(w -> w.length() > 2);
        System.out.println("filter 的使用结果：" + longWords.collect(Collectors.joining(",")));

        Stream<String> uppercaseWords = words.stream().map(String::toUpperCase);
        System.out.println("map 的使用结果：" + uppercaseWords.collect(Collectors.joining(",")));

        Stream<Stream<String>> result = words.stream().map(ToolUtil::letters);
        Stream<String> flatResult = words.stream().flatMap(ToolUtil::letters);
        System.out.println("flatMap 的使用结果：" + flatResult.collect(Collectors.joining(",")));
    }
}
