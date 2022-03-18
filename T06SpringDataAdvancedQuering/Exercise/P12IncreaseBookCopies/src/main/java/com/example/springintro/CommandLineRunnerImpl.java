package com.example.springintro;

import com.example.springintro.model.entity.EditionType;
import com.example.springintro.model.entity.SelectedBookInfo;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter date (DD MMM YYYY):");
        String dateText = scanner.nextLine();

        System.out.println("Enter additional number of copies:");
        int additionalCopies = Integer.parseInt(scanner.nextLine());

        int booksChanged = increaseNumberOfCopiesForBooksReleasedAfter(dateText, additionalCopies);

        System.out.println(booksChanged * additionalCopies);
    }

    private int increaseNumberOfCopiesForBooksReleasedAfter(String dateText, int additionalCopies) {

        return this.bookService.increaseCopiesForBooksReleasedAfter(dateText, additionalCopies);
    }

    private void printPartialBookInformation(String bookTitle) {
        SelectedBookInfo bookInfo = this.bookService.findPartialBookInfoByTitle(bookTitle);
        System.out.printf("%s %s %s %s%n", bookInfo.getTitle(), bookInfo.getEditionType(),
                bookInfo.getAgeRestriction(), bookInfo.getPrice());
    }

    private void printAuthorsWithTotalCopiesCount() {
        this.authorService.findAllAuthorsAndTheirTotalCopies()
                .forEach(a -> System.out.printf("%s - %d%n", a.getFullName(), a.getTotalCopies()));
    }

    private void printBooksWithTitleLongerThan(int minimumTitleLength) {
        long numberOfBooks = this.bookService.findAllBooksWithTitleLongerThan(minimumTitleLength);
        System.out.printf("There are %d books with longer title than %d symbols%n", numberOfBooks, minimumTitleLength);
    }

    private void printBooksAreWrittenByAuthorWithLastNameStartingWith(String textToSearch) {
        this.bookService.findAllBooksWithAuthorLastNameStartingWith(textToSearch)
            .forEach(book -> System.out.printf("%s (%s)%n", book.getTitle(), book.getAuthor()));
    }

    private void printBooksThatContainText(String textToSearch) {
        this.bookService.findAllBooksThatContainText(textToSearch)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void printAuthorsThatFirstNameEndAt(String lastLettersOfFirstName) {
        this.authorService.findAllAuthorsWithFirstNameEndingWith(lastLettersOfFirstName)
                .forEach(System.out::println);
    }

    private void printBooksReleasedBefore(String date) {
        this.bookService.findAllBooksReleasedBeforeDate(date)
                .forEach(System.out::println);
    }

    private void printBookTitlesForBooksNotReleasedInYear(int year) {
        this.bookService.findAllBooksNotInYear(year)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void printAllBooksByPrice() {
        this.bookService.findAllBooksByPriceLowerThanAndHigherThan(5, 40)
                .forEach(book -> System.out.printf("%s - $%s%n", book.getTitle(), book.getPrice()));
    }

    private void printAllBooksByEditionAndCopiesLessThan5000() {
        this.bookService.findAllBooksByEditionAndCopies(EditionType.GOLD, 5000)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void printAllBookTitlesByAgeRestriction(String ageRestrictionText) {
        this.bookService.findAllBooksByAgeRestriction(ageRestrictionText)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
