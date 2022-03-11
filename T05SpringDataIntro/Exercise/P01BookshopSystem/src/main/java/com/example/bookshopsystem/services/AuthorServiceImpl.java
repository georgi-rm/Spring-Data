package com.example.bookshopsystem.services;

import com.example.bookshopsystem.models.Author;
import com.example.bookshopsystem.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {
        long countOfAllAuthors = this.authorRepository.count();


        while (true) {
            Long randomAuthorId = new Random().nextLong(countOfAllAuthors) + 1;
            Optional<Author> author = this.authorRepository.findById(randomAuthorId);
            if (author.isPresent()) {
                return author.get();
            }
        }
    }

    @Override
    public List<Author> getAuthorWithBookReleasedBefore1990() {
        LocalDate year = LocalDate.of(1990, 1, 1);
        return authorRepository.findDistinctByBooksReleaseDateBefore(year);
    }

    @Override
    public List<Author> getAllAuthorsOrderedByCountOfTheirBooks() {
        List<Author> authors = authorRepository.findAll();

        return authors.stream()
                .sorted((f, s) -> Integer.compare(s.getBooks().size(), f.getBooks().size()))
                .collect(Collectors.toList());
    }

    @Override
    public Author getAuthorGeorgePowell() {
        return authorRepository.getAuthorByFirstNameAndLastName("George", "Powell");
    }
}
