package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;

import java.util.List;

public interface ShampooService {
    List<Shampoo> findShampoosBySize(String size);

    List<Shampoo> findShampoosBySizeOrLabel(String size, Long labelId);

    List<Shampoo> findShampoosByPriceHigherThan(Double price);

    int findCountOfShampooByPriceLowerThan(Double price);

    List<Shampoo> findShampoosByIngredients(List<String> ingredients);
}
