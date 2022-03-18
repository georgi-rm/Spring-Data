package com.example.springintro.service;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.AuthorWithTotalCopies;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<Author> findAllAuthorsWithFirstNameEndingWith(String lastLettersOfFirstName);

    List<AuthorWithTotalCopies> findAllAuthorsAndTheirTotalCopies();
}
