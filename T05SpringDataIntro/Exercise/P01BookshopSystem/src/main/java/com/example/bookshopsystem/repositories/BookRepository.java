package com.example.bookshopsystem.repositories;

import com.example.bookshopsystem.models.Author;
import com.example.bookshopsystem.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByReleaseDateAfter(LocalDate releaseDate);

    List<Book> findAllByAuthorOrderByReleaseDateDescTitleAsc(Author author);
}
