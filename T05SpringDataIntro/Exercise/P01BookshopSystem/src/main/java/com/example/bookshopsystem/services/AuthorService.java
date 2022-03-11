package com.example.bookshopsystem.services;

import com.example.bookshopsystem.models.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    Author getRandomAuthor();
    List<Author> getAuthorWithBookReleasedBefore1990();

    List<Author> getAllAuthorsOrderedByCountOfTheirBooks();

    Author getAuthorGeorgePowell();
}
