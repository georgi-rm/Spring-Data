package com.example.advquerying;

import com.example.advquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ShampooService shampooService;

    @Autowired
    public ConsoleRunner(ShampooService shampooService) {
        this.shampooService = shampooService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Price:");
        Double price = Double.parseDouble(scanner.nextLine());

        this.shampooService.findShampoosByPriceHigherThan(price)
                .forEach(System.out::println);
    }
}
