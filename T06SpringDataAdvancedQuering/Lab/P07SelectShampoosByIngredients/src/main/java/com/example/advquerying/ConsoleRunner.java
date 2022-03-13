package com.example.advquerying;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ShampooService shampooService;

    private final IngredientService ingredientService;

    @Autowired
    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Ingredients:");
        List<String> ingredientNames = Arrays.stream(scanner.nextLine().split("\\s+")).toList();

        List<Shampoo> shampoos = this.shampooService.findShampoosByIngredients(ingredientNames);

        Set<String> shampooNames = new LinkedHashSet<>();

        shampoos.stream()
                .map(Shampoo::getBrand)
                .forEach(shampooNames::add);

        shampooNames.forEach(System.out::println);
    }
}
