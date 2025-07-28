package top30;

import java.util.*;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


//top 30 questions
public class Stream30 {

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

        List<String> input1 = List.of("cat", "dog", "elephant", "ant", "dolphin", "bat", "jackals");
        Map<Integer, String> collect2 = input1.stream()
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(String::length)),
                                optional -> optional.orElse(null)
                        )
                ));

//        collect2.forEach((k , v) ->
//                System.out.println(k + " " + v));

//        5. Custom Collector - Concatenate with Delimiter
//        Problem:
//        Create a custom collector that joins strings with a delimiter, prefix, and suffix.

        List<String> names = List.of("Alice", "Bob", "Charlie");
        String collect3 = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining("|", "[", "]"));
//        System.out.println(collect3);
//        Expected Output:
//[Alice|Bob|Charlie] (with | as delimiter, [ prefix, and ] suffix)

//        ------------------------------------------------------------

        List<Person> people = List.of(
                new Person("Alice", "New York", 25),
                new Person("Bob", "New York", 35),
                new Person("Charlie", "Los Angeles", 45),
                new Person("David", "New York", 55),
                new Person("Eva", "Los Angeles", 28),
                new Person("Frank", "Chicago", 60),
                new Person("Grace", "Chicago", 32),
                new Person("Helen", "Chicago", 22)
        );
//        6. Multi-Level Grouping
//        Problem:
//        Given a list of Person(name, city, age), group by city, then by age group (<30, 30-50, >50).
        Map<String, Map<String, List<Person>>> collect4 = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.groupingBy(person -> {
                                    int age = person.getAge();

                                    if (age < 30)
                                        return "30";
                                    else if (age <= 50)
                                        return "30 - 50";
                                    else
                                        return ">50";
                                }
                        )
                ));
//
//        collect4.forEach((k , v) ->{
//            System.out.println(k);
//            v.forEach((k1 , v1) ->{
//                System.out.println(k1);
//                v1.forEach(System.out::print);
//                System.out.println();
//            });
//            System.out.println();
//        });

//        -------------------------------------------------------------

//        💡 7. Transform List to Map with Merging
//        Problem:
//        Given a list of transactions (user, amount), return a map of total amount per user.

        class Transaction{
            String name;
            Integer amount;

            public Transaction(String name , Integer amount){
                this.name = name;
                this.amount = amount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Integer getAmount() {
                return amount;
            }

            public void setAmount(Integer amount) {
                this.amount = amount;
            }
        }
        List<Transaction> tx = List.of(
                new Transaction("Alice", 50),
                new Transaction("Bob", 20),
                new Transaction("Alice", 70)
        );

        Map<String, Integer> collect5 = tx.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getName,
//                        Collectors.summingInt(Transaction::getAmount)
                        Collectors.reducing(0 , Transaction::getAmount , Integer::sum)
                ));
//        collect5.forEach((k , v) ->{
//            System.out.println(k + " " + v);
//        });

//---------------------------------------------------------------------------------------------

//        Problem 8:
//        Partition a list of integers into two groups: prime and non-prime numbers.

        List<Integer> nums = new ArrayList<>(List.of(1,2,3,4,5,6,7,8,9,10));

        //                            if(isPrime(n))
        //                                return "Prime";
        //                            else
        //                                return "Non Prime";
        Map<String, List<Integer>> collect6 = nums.stream()
                .collect(Collectors.groupingBy(
                        n -> isPrime(n) ? "Prime" : "Non Prime"
                ));

//        collect6.forEach((k , v) ->{
//            System.out.println(k);
//            v.forEach(x -> System.out.print( x + " "));
//            System.out.println();
//        });

//      9. Find Most Common Character
//        Given a string, find the most frequently occurring character (excluding whitespace).
        String text = "Stream API is powerful";

        Character c = text.chars()
                .mapToObj(ch -> (char) ch)
                .filter(Character::isLetter)
                .map(Character::toLowerCase)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse('?');
//        System.out.println(c);

//        10. Find First Non-Repeated Character
//        Problem:
//        Given a string, find the first non-repeating character using streams.

        String input2 = "swisswwi";
        Character c1 = input2.chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        HashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .filter(ch -> ch.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse('?');
//        System.out.println(c1);
    }


    private static boolean isPrime(int n){
        if(n == 1)
            return false;
        for(int i=2;i*i <= n;i++){
            if(n%i == 0)return false;
        }
        return true;
    }


}
