package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> findIngredientsByName(String startingLetter) {
        return ingredientRepository.findAllByNameStartingWith(startingLetter);
    }

    @Override
    public List<Ingredient> findIngredientsByNames(List<String> names) {
        return ingredientRepository.findAllByNameInOrderByPriceAsc(names);
    }

    @Override
    public int deleteIngredientByName(String name) {
        return ingredientRepository.deleteByName(name);
    }
}
