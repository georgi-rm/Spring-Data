package com.example.springintro.service;

import com.example.springintro.model.entity.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksByAgeRestriction(String ageRestrictionText);
}
