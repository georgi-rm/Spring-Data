package com.example.springintro.service;

import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksByAgeRestriction(String ageRestrictionText);

    List<Book> findAllBooksByEditionAndCopies(EditionType editionType, int numberOfCopies);

    List<Book> findAllBooksByPriceLowerThanAndHigherThan(double lower, double higher);

    List<Book> findAllBooksNotInYear(int year);
}
