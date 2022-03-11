package com.example.bookshopsystem.services;

import com.example.bookshopsystem.models.Author;
import com.example.bookshopsystem.models.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooksAfterYear2000();

    List<Book> getAllBookFromAuthorOrderedByDateAndTitle(Author author);
}
