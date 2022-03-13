package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> findShampoosBySize(String size) {

        Size shampooSize = Size.valueOf(size.toUpperCase());

        return shampooRepository.findAllBySizeOrderByIdAsc(shampooSize);
    }

    @Override
    public List<Shampoo> findShampoosBySizeOrLabel(String size, Long labelId) {
        Size shampooSize = Size.valueOf(size.toUpperCase());

        return shampooRepository.findAllBySizeOrLabelIdOrderByPrice(shampooSize, labelId);
    }

    @Override
    public List<Shampoo> findShampoosByPriceHigherThan(Double price) {
        return shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal.valueOf(price));
    }

    @Override
    public int findCountOfShampooByPriceLowerThan(Double price) {
        BigDecimal priceOfShampoo = BigDecimal.valueOf(price);
        return shampooRepository.countByPriceLessThan(priceOfShampoo);
    }
}
