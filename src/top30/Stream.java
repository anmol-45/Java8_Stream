package top30;

import java.util.*;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Stream {

    public static void main(String[] args) {

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

        collect.forEach((k , v) ->
                System.out.println(k + " " + v)
                );


    }
}
