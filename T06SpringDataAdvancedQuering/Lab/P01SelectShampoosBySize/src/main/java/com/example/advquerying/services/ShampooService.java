package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;

import java.util.List;

public interface ShampooService {
    List<Shampoo> findShampoosBySize(String size);
}
