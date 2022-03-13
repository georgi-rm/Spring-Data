package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IngredientService {
    List<Ingredient> findIngredientsByName(String startingLetter);

    List<Ingredient> findIngredientsByNames(List<String> names);

    int deleteIngredientByName(String name);
}
