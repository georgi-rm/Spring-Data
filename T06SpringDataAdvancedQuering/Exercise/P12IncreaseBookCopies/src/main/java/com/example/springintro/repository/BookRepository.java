package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.model.entity.SelectedBookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int numberOfCopies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal price, BigDecimal price2);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate firstDayOfYear, LocalDate lastDayOfYear);

    List<Book> findAllByTitleContaining(String textToSearch);

    List<Book> findAllByAuthorLastNameStartingWith(String textToSearch);

    @Query("SELECT count(b) FROM Book b WHERE length(b.title) > :minimumTitleLength")
    long countBooksByTitleLessThan(int minimumTitleLength);

    @Query("SELECT title AS title, editionType AS editionType, ageRestriction AS ageRestriction, price AS price FROM Book" +
            " WHERE title = :title")
    SelectedBookInfo findPartialInfoForBookByTitle(String title);

    @Transactional
    @Modifying
    @Query("UPDATE Book AS b" +
            " SET b.copies = b.copies + :additionalCopies" +
            " WHERE b.releaseDate > :date")
    int increaseCopiesForBooksReleasedAfter(LocalDate date, int additionalCopies);
}
