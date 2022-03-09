package com.example.bookshopsystem.services;

import com.example.bookshopsystem.models.Author;
import com.example.bookshopsystem.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

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
}
