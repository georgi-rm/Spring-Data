package com.example.bookshopsystem.services;

import com.example.bookshopsystem.models.Category;
import com.example.bookshopsystem.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {
        long countOfAllCategories = this.categoryRepository.count();

        Random random = new Random();

        long numberOfCategories = random.nextLong(countOfAllCategories) + 1;

        Set<Long> idSet = new HashSet<>();

        for (int i = 0; i < numberOfCategories; i++) {
            Long randomCategoryId = random.nextLong(countOfAllCategories) + 1;
            idSet.add(randomCategoryId);
        }

        Set<Category> randomCategories = new HashSet<>();
        for (Long id : idSet) {
            Optional<Category> category = this.categoryRepository.findById(id);
            category.ifPresent(randomCategories::add);
        }

        return randomCategories;
    }
}
