package top30;

import java.util.*;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringQuestions {

    public static void main(String[] args) {

//        1. Extract all email addresses from a list of sentences
//        Input:

        List<String> sentences = List.of(
                "Contact us at support@example.com",
                "Send feedback to feedback@myapp.io or info@myapp.io"
        );

        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");

        List<String> email = sentences.stream()
                .flatMap(
                        sentence ->{

                                Matcher m = emailPattern.matcher(sentence);
                                return m.results().map(MatchResult::group);
                        }
                )
                .toList();
//        for (String s : email) {
//            System.out.println(s);
//
//        }
//
//        2. Find the most frequent word in a paragraph (case-insensitive)
//        Input:

        String paragraph = "Java is great. Java streams are powerful. Streams are fun!";

        String s = Arrays.stream(paragraph.split(" "))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.<String, Long>comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(" ");
        System.out.println(s);

    }
}
