package com.example.bookshopsystem.services;

import com.example.bookshopsystem.models.Author;
import com.example.bookshopsystem.models.Book;
import com.example.bookshopsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooksAfterYear2000() {
        LocalDate year = LocalDate.of(2000, 12, 31);

        return bookRepository.findAllByReleaseDateAfter(year);
    }

    @Override
    public List<Book> getAllBookFromAuthorOrderedByDateAndTitle(Author author) {
        return bookRepository.findAllByAuthorOrderByReleaseDateDescTitleAsc(author);
    }
}
