package top30;

import java.util.*;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Stream {

    public static void main(String[] args) {

//        1. Top N Frequent Words
//        Problem:
//        Given a list of strings representing words, find the top 3 most frequent words.
        List<String> words = List.of("apple", "banana", "apple", "grapes", "orange", "banana", "apple");

//        solution:
        LinkedHashMap<String, Long> collect = words.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new
                ));

//        collect.forEach((k , v) ->
//                System.out.println(k + " " + v)
//                );

//        2. Flatten and Filter Nested Lists
//        Problem:
//        Given a list of lists of integers, flatten it into a single list and remove all even numbers.

        List<List<Integer>> input = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );

        List<Integer> list = input.stream()
                .flatMap(x -> x.stream().filter(n -> n % 2 == 1)).toList();
//
//        list.forEach(System.out::print);
//        System.out.println();

//        3. Group by First Letter
//        Problem:
//        Given a list of strings, group them by their first character.

        List<String> items = List.of("apple", "banana", "avocado", "blueberry", "apricot");

        Map<Character, List<String>> collect1 = items.stream()
                .collect(Collectors.groupingBy(
                        s -> s.charAt(0)
                ));
//        collect1.forEach((k , v) ->
//                System.out.println(k +" " + v)
//        );

//        4. Longest String by Group
//        Problem:
//        Group strings by length, and get the longest string in each group.

        List<String> input1 = List.of("cat", "dog", "elephant", "ant", "dolphin", "bat", "aackals");
        Map<Integer, String> collect2 = input1.stream()
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(String::length)),
                                optional -> optional.orElse(null)
                        )
                ));

        collect2.forEach((k , v) ->
                System.out.println(k + " " + v));


    }
}
