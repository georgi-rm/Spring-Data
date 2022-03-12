package com.example.advquerying.services;

import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
