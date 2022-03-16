package com.example.springintro.repository;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.AuthorWithTotalCopies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {


    List<Author> findAllByFirstNameEndingWith(String lastLettersOfFirstName);

    @Query("SELECT concat(a.firstName, ' ', a.lastName) AS fullName," +
            " sum(b.copies) AS totalCopies" +
            " FROM Author AS a" +
            " JOIN a.books AS b" +
            " GROUP BY a.id" +
            " ORDER BY totalCopies DESC")
    List<AuthorWithTotalCopies> findAuthorsAndTheirTotalCopies();
}
