package com.example.bookshopsystem.models;

import com.example.bookshopsystem.enumerations.AgeRestriction;
import com.example.bookshopsystem.enumerations.EditionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "edition_type", nullable = false)
    private EditionType editionType;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int copies;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "age_restriction", nullable = false)
    private AgeRestriction ageRestriction;

    @ManyToOne
    private Author author;

    @ManyToMany()
    private Set<Category> categories;


    public Book() {
    }

    public Book(String title, EditionType editionType, BigDecimal price, LocalDate releaseDate, AgeRestriction ageRestriction, Author author, Set<Category> categories, int copies) {
        this.title = title;
        this.editionType = editionType;
        this.price = price;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
        this.author = author;
        this.categories = categories;
        this.copies = copies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Author getAuthor() {
        return author;
    }
}
