package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByNameStartingWith(String startingLetter);

    List<Ingredient> findAllByNameInOrderByPriceAsc(List<String> names);

    @Transactional
    int deleteByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Ingredient SET price = price * 1.1")
    int increasePriceByTenPercent();

    @Transactional
    @Modifying
    @Query("UPDATE Ingredient SET price = price * 1.1" +
            " WHERE name IN :names")
    int increasePriceForChosenItems(List<String> names);
}
