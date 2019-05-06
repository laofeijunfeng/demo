package com.linjunfeng.demo.stream.demo8;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo8_1 {
    public static Stream<String> noVoewls() {
        List<String> words = Arrays.asList("abb", "ebb", "cccc", "ddd", "eeeee", "ccc");
        Stream<String> wordList = words.stream();
        return wordList.map(s -> s.replaceAll("[aeiouAEIOU]", ""));
    }

    public static <T> void show(String label, Set<T> set) {
        System.out.println(label + ": " + set.getClass().getName());
        System.out.println("[" + set.stream().limit(10).map(Objects::toString).collect(Collectors.joining(", ")) + "]");
    }

    public static void main(String[] args) {
        Iterator<Integer> iter = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (iter.hasNext())
            System.out.println(iter.next());

        Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
        System.out.println("Object array: " + numbers);

        try {
            Integer number = (Integer)numbers[0];
            System.out.println("number: " + number);
            System.out.println("The Following statement throws an exception");
            Integer[] numbers2 = (Integer[])numbers;
        } catch (ClassCastException e) {
            System.out.println(e);
        }

        Integer[] numbers3 = Stream.iterate(0, n -> n + 1).limit(10).toArray(Integer[]::new);
        System.out.println("Integer array: " + numbers3);

        Set<String> noVowelSet = noVoewls().collect(Collectors.toSet());
        show("noVowelSet", noVowelSet);

        TreeSet<String> noVowelTreeSet = noVoewls().collect(Collectors.toCollection(TreeSet::new));
        show("noVowelTreeSet", noVowelTreeSet);

        String result = noVoewls().limit(10).collect(Collectors.joining());
        System.out.println("joining: " + result);
        result = noVoewls().limit(10).collect(Collectors.joining(", "));
        System.out.println("joining with commas: " + result);

        IntSummaryStatistics summaryStatistics = noVoewls().collect(Collectors.summarizingInt(String::length));
        double averageWordLength = summaryStatistics.getAverage();
        double maxWordLength = summaryStatistics.getMax();
        System.out.println("Average word length: " + averageWordLength);
        System.out.println("Max word length: " + maxWordLength);
        System.out.println("forEach: ");
        noVoewls().limit(10).forEach(System.out::println);
    }
}
