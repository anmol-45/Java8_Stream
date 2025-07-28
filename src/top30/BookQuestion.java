package top30;

import java.util.*;
import java.util.stream.Collectors;

public class BookQuestion {
    public static void main(String[] args) {
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
//        System.out.println(collect10);


//        12. Get a list of books published in the 20th century (1901–2000)

        List<Book> list5 = books.stream()
                .filter((b -> {
                            int year = b.getPublishedYear();
                            return year >= 1901 && year <= 2000;
                        })
                )
                .toList();

//        13. Get distinct genres

        List<String> list = books.stream()
                .map(Book::getGenre)
                .distinct()
                .toList();

//        14. Find if any book has more than 800 pages

        boolean b1 = books.stream()
                .anyMatch(b -> b.getPages() >= 800);

//        15. Get total price of all fantasy books

        double fantasy1 = books.stream()
                .filter(b -> b.getGenre().equalsIgnoreCase("fantasy"))
                .mapToDouble(Book::getPrice)
                .sum();

//        ---------------------------------------------------------------------------------------------

//        NESTED QUERIES
//        WITH AUTHOR AND BOOKS

        List<Author> authors = getAuthors();
//        1. INNER JOIN: Get book title and author name

        List<String> list6 = books.stream()
                .flatMap(
                        b -> authors.stream()
                                .filter(author -> author.getId() == b.getAuthorId())
                                .map(author -> b.getTitle() + " BY " + author.getName())
                )
                .toList();
        list6.forEach(System.out::println);

//        2. LEFT JOIN: Include books even if author not found

        List<String> anonymous = books.stream()
                .map(b -> {
                    String authorName =  authors.stream()
                            .filter(auth -> auth.getId() == b.getAuthorId())
                            .map(Author::getName)
                            .findFirst()
                            .orElse("Anonymous");

                    return b.getTitle() + " BY " + authorName;
                }).toList();

//        3. RIGHT JOIN: Include authors even if no books (simulate with authors as main list)

        List<String> unknownBook1 = authors.stream()
                .map(author -> {
                    String unknownBook = books.stream()
                            .filter(b -> b.getAuthorId() == author.getId())
                            .map(Book::getTitle)
                            .findFirst()
                            .orElse("Unknown Book");

                    return author.getName() + "writter: " + unknownBook;
                }).toList();

    }

    private static List<Book> getBooks(){
        List<Book> books = new ArrayList<>();

        books.add(new Book(1, "Clean Code", "Robert Martin", "Programming", 45.0, 4.8, 464, 2008,1));
        books.add(new Book(2, "Effective Java", "Joshua Bloch", "Programming", 55.0, 4.7, 416, 2018,2));
        books.add(new Book(3, "The Hobbit", "J.R.R. Tolkien", "Fantasy", 25.0, 4.9, 310, 1937, 3));
        books.add(new Book(4, "The Pragmatic Programmer", "Andy Hunt", "Programming", 50.0, 4.6, 352, 1999, 4));
        books.add(new Book(5, "Harry Potter", "J.K. Rowling", "Fantasy", 30.0, 4.8, 500, 1997, 5));
        books.add(new Book(6, "Code Complete", "Steve McConnell", "Programming", 60.0, 4.7, 914, 2004,6));
        books.add(new Book(7, "Game of Thrones", "George R.R. Martin", "Fantasy", 40.0, 4.5, 694, 1996,7));
        return books;
    }
    private static List<Author> getAuthors(){

        return List.of(
                new Author(1, "Robert Martin", "USA"),
                new Author(2, "Joshua Bloch", "USA"),
                new Author(5, "J.K. Rowling", "UK"),
                new Author(4, "Andy Hunt", "UK"),
                new Author(3, "J.R.R. Tolkien", "UK"),
                new Author(6, "Steve McConnell", "UK"),
                new Author(7, "George R.R. Martin", "UK")
        );
    }
}
