package com.linjunfeng.demo.stream.demo6;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Demo6_1 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("a", "bb", "cccc", "ddd", "eeeee", "ccc");
        Optional<String> largest = words.stream().max(String::compareToIgnoreCase);
        System.out.println("largest: " + largest.orElse(""));

        Optional<String> startWithC = words.stream().filter(s -> s.startsWith("c")).findFirst();
        System.out.println("findFirst: " + startWithC.orElse(""));

        Optional<String> anyWithC = words.stream().filter(s -> s.startsWith("c")).findAny();
        System.out.println("findAny: " + anyWithC.orElse(""));

        boolean aWordStartWithB = words.stream().parallel().allMatch(s -> s.startsWith("c"));
        System.out.println("aWordStartWithB: " + aWordStartWithB);
    }
}
