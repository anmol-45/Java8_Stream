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
//
//        java
//                Copy
//        Edit
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

//        Problem:
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



        //working on books data

        List<Book> books = getBooks();

//        1. Get a list of all book titles
        List<String> list1 = books.stream()
                .map(Book::getTitle)
                .toList();
//        list1.forEach(s -> System.out.println(s + " "));

//        2. Filter books with rating >= 4.8
        List<Book> list2 = books.stream()
                .filter(b -> b.getRating() >= 4.8)
                .toList();

//        3. Get the average price of programming books
        double programming = books.stream()
                .filter(b -> b.getGenre().equalsIgnoreCase("programming"))
                .mapToDouble(Book::getPrice)
                .average().orElse(0);
//        System.out.println(programming);

//        4. Get a map of genre to list of book titles
        Map<String, List<String>> collect7 = books.stream()
                .collect(Collectors.groupingBy(
                        Book::getGenre,
                        Collectors.mapping(Book::getTitle ,Collectors.toList())
                ));

//        5. find the most expensive book
        Book book = books.stream()
                .max(Comparator.comparingDouble(Book::getPrice))
                .orElse(null);
//        System.out.println(book);

//        6. Group books by author and count how many books they wrote

        Map<String, Long> collect8 = books.stream()
                .collect(Collectors.groupingBy(
                        Book::getAuthor,
                        Collectors.counting()
                ));

//        7. Partition books into old (before 2000) and new

        Map<String, List<Book>> collect9 = books.stream()
                .collect(Collectors.groupingBy(
                        b -> {
                            if (b.getPublishedYear() >= 2000)
                                return "NEW";
                            else
                                return "OLD";
                        }
                ));

//        8. Get average number of pages for fantasy books

        double fantasy = books.stream()
                .filter(b -> b.getGenre().equalsIgnoreCase("fantasy"))
                .mapToInt(Book::getPages)
                .average().orElse(0.0);

//        9. Sort all books by rating descending then by price ascending

//        desc and then asc
        List<Book> list3 = books.stream()
                .sorted(Comparator
                        .comparingDouble(Book::getRating)
                        .reversed()
                        .thenComparing((Book::getPrice))
                ).toList();

//        Both descending using reverse on different comparator
//        List<Book> list3 = books.stream()
//                .sorted(Comparator
//                        .comparingDouble(Book::getRating)
//                        .reversed()
//                        .thenComparing(Comparator.comparingDouble(Book::getPrice).reversed())
//                ).toList();

//        Asc and then Desc using comparator inside then comparing
//        List<Book> list3 = books.stream()
//                .sorted(Comparator
//                        .comparingDouble(Book::getRating)       //asc
//                        .thenComparing(Comparator.comparingDouble(Book::getPrice).reversed())   //desc
//                ).toList();

//        Both Desc when applying reversing on first comparator
//        List<Book> list3 = books.stream()
//                .sorted(Comparator
//                        .comparingDouble(Book::getRating)
//                        .thenComparingDouble(Book::getPrice)
//                        .reversed()
//                ).toList();

//        list3.forEach(System.out::println);

//        triple comparison for sorting
        List<Book> list4 = books.stream()
                .sorted(Comparator
                        .comparingDouble(Book::getRating)   //desc sorting
                        .reversed()
                        .thenComparing(Book::getPrice)  //asc and if the price is same
                        .thenComparing(Comparator.comparing(Book::getAuthor).reversed()) //sorting in desc according to author
                ).toList();

//        10. Find the longest book (by pages) in the Programming genre
        Book programming1 = books.stream()
                .filter(b -> b.getGenre().equalsIgnoreCase("programming"))
                .max(Comparator.comparingInt(Book::getPages))
                .orElse(null);

//        11. Create a comma-separated string of all book titles
        String collect10 = books.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining(","));
        System.out.println(collect10);
//        12. Get a list of books published in the 20th century (1901–2000)

        List<Book> list5 = books.stream()
                .filter((b -> {
                            int year = b.getPublishedYear();
                            return year >= 1901 && year <= 2000;
                        })
                )
                .toList();

    }


    private static boolean isPrime(int n){
        if(n == 1)
            return false;
        for(int i=2;i*i <= n;i++){
            if(n%i == 0)return false;
        }
        return true;
    }

    private static List<Book> getBooks(){
        List<Book> books = new ArrayList<>();

        books.add(new Book(1, "Clean Code", "Robert Martin", "Programming", 45.0, 4.8, 464, 2008));
        books.add(new Book(2, "Effective Java", "Joshua Bloch", "Programming", 55.0, 4.7, 416, 2018));
        books.add(new Book(3, "The Hobbit", "J.R.R. Tolkien", "Fantasy", 25.0, 4.9, 310, 1937));
        books.add(new Book(4, "The Pragmatic Programmer", "Andy Hunt", "Programming", 50.0, 4.6, 352, 1999));
        books.add(new Book(5, "Harry Potter", "J.K. Rowling", "Fantasy", 30.0, 4.8, 500, 1997));
        books.add(new Book(6, "Code Complete", "Steve McConnell", "Programming", 60.0, 4.7, 914, 2004));
        books.add(new Book(7, "Game of Thrones", "George R.R. Martin", "Fantasy", 40.0, 4.5, 694, 1996));
        return books;
    }
}
