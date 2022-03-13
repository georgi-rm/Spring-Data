package com.example.advquerying.repositories;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findAllBySizeOrderByIdAsc(Size shampooSize);

    List<Shampoo> findAllBySizeOrLabelIdOrderByPrice(Size shampooSize, Long labelId);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal valueOf);

    int countByPriceLessThan(BigDecimal priceOfShampoo);

    @Query("SELECT s FROM Shampoo AS s" +
            " JOIN s.ingredients AS i" +
            " WHERE i.name IN :ingredientNames")
    List<Shampoo> findAllByIngredientsIn(@Param("ingredientNames") List<String> ingredients);

    @Query("SELECT s FROM Shampoo AS s" +
            " WHERE s.ingredients.size < 2")
    List<Shampoo> findAllByIngredientsCountLessThan(int limit);
}
