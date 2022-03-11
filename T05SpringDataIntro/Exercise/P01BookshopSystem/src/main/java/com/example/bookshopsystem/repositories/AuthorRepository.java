package com.example.bookshopsystem.repositories;

import com.example.bookshopsystem.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate releaseDate);

    List<Author> findAll();

    Author getAuthorByFirstNameAndLastName(String firstName, String secondName);
}
