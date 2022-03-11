package com.example.bookshopsystem;

import com.example.bookshopsystem.enumerations.AgeRestriction;
import com.example.bookshopsystem.enumerations.EditionType;
import com.example.bookshopsystem.models.Author;
import com.example.bookshopsystem.models.Book;
import com.example.bookshopsystem.models.Category;
import com.example.bookshopsystem.repositories.AuthorRepository;
import com.example.bookshopsystem.repositories.BookRepository;
import com.example.bookshopsystem.repositories.CategoryRepository;
import com.example.bookshopsystem.services.AuthorService;
import com.example.bookshopsystem.services.BookService;
import com.example.bookshopsystem.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private static final String RESOURCE_PATH = "src/main/resources/files/";
    private static final String BOOKS_FILE_NAME = "books.txt";
    private static final String AUTHORS_FILE_NAME = "authors.txt";
    private static final String CATEGORIES_FILE_NAME = "categories.txt";

    BookService bookService;
    AuthorService authorService;
    CategoryService categoryService;
    BookRepository bookRepository;
    AuthorRepository authorRepository;
    CategoryRepository categoryRepository;

    public ConsoleRunner(BookService bookService, AuthorService authorService, CategoryService categoryService, BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDatabase();

        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Enter query number(1-4) or End:");
        String input = scanner.nextLine();
        while (!input.equals("End")) {
            switch (input) {
                case "1":
                    bookService.getAllBooksAfterYear2000().stream()
                            .map(Book::getTitle)
                            .forEach(System.out::println);
                    break;
                case "2":
                    authorService.getAuthorWithBookReleasedBefore1990()
                            .forEach(a -> System.out.printf("%s %s%n", a.getFirstName(), a.getLastName()));
                    break;
                case "3":
                    authorService.getAllAuthorsOrderedByCountOfTheirBooks()
                            .forEach(a -> System.out.printf("%s %s - %d books%n", a.getFirstName(), a.getLastName(), a.getBooks().size()));
                    break;
                case "4":
                    Author author = authorService.getAuthorGeorgePowell();
                    bookService.getAllBookFromAuthorOrderedByDateAndTitle(author)
                            .forEach(b -> System.out.printf("%s %s - %d copies%n", b.getTitle(), b.getReleaseDate().toString(), b.getCopies()));
                    break;
            }
            System.out.println();
            System.out.println("Enter query number(1-4) or End:");
            input = scanner.nextLine();
        }
    }

    void seedDatabase() throws IOException {
        seedAuthors();
        seedCategories();
        seedBooks();
    }

    private void seedAuthors() throws IOException {
        Files.readAllLines(Path.of(RESOURCE_PATH + AUTHORS_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .forEach(row -> {
                    String[] data = row.split(" ");

                    String authorFirstName = data[0];
                    String authorLastName = data[1];

                    Author author = new Author(authorFirstName, authorLastName );
                    authorRepository.save(author);
                });
    }

    private void seedCategories() throws IOException {
        Files.readAllLines(Path.of(RESOURCE_PATH + CATEGORIES_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .forEach(row -> {
                    Category category = new Category(row);
                    categoryRepository.save(category);
                });
    }

    private void seedBooks() throws IOException {
        Files.readAllLines(Path.of(RESOURCE_PATH + BOOKS_FILE_NAME))
                .forEach(row -> {
                    String[] data = row.split("\\s+");

                    Author author = authorService.getRandomAuthor();
                    EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
                    LocalDate releaseDate = LocalDate.parse(data[1],
                            DateTimeFormatter.ofPattern("d/M/yyyy"));
                    int copies = Integer.parseInt(data[2]);
                    BigDecimal price = new BigDecimal(data[3]);
                    AgeRestriction ageRestriction = AgeRestriction
                            .values()[Integer.parseInt(data[4])];
                    String title = Arrays.stream(data)
                            .skip(5)
                            .collect(Collectors.joining(" "));
                    Set<Category> categories = categoryService.getRandomCategories();
                    for (Category category : categories) {
                        System.out.printf("%s -> %d%n", category.toString(), category.hashCode());
                    }
                    Book book = new Book(title, editionType, price, releaseDate,
                            ageRestriction, author, categories, copies);

                    bookRepository.save(book);
                });
    }
}
