package top30;



    public class Book {
        int id;
        String title;
        String author;
        int authorId;
        String genre;
        double price;
        double rating;
        int pages;
        int publishedYear;

        public Book(int id, String title, String author, String genre, double price, double rating, int pages, int publishedYear, int authorId) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.price = price;
            this.rating = rating;
            this.pages = pages;
            this.publishedYear = publishedYear;
            this.authorId = authorId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPublishedYear() {
            return publishedYear;
        }

        public void setPublishedYear(int publishedYear) {
            this.publishedYear = publishedYear;
        }

        public int getAuthorId() {
            return authorId;
        }

        @Override
        public String toString() {
            return String.format("Book{id=%d, title='%s', author='%s', genre='%s', price=%.2f, rating=%.1f, pages=%d, publishedYear=%d}",
                    id, title, author, genre, price, rating, pages, publishedYear);
        }

    }

//    public static void main(String[] args) {
//        List<Book> books = new ArrayList<>();
//
//        books.add(new Book(1, "Clean Code", "Robert Martin", "Programming", 45.0, 4.8, 464, 2008));
//        books.add(new Book(2, "Effective Java", "Joshua Bloch", "Programming", 55.0, 4.7, 416, 2018));
//        books.add(new Book(3, "The Hobbit", "J.R.R. Tolkien", "Fantasy", 25.0, 4.9, 310, 1937));
//        books.add(new Book(4, "The Pragmatic Programmer", "Andy Hunt", "Programming", 50.0, 4.6, 352, 1999));
//        books.add(new Book(5, "Harry Potter", "J.K. Rowling", "Fantasy", 30.0, 4.8, 500, 1997));
//        books.add(new Book(6, "Code Complete", "Steve McConnell", "Programming", 60.0, 4.7, 914, 2004));
//        books.add(new Book(7, "Game of Thrones", "George R.R. Martin", "Fantasy", 40.0, 4.5, 694, 1996));
//
//        // Example: print all books
//        for (Book book : books) {
//            System.out.println(book);
//        }
//    }
