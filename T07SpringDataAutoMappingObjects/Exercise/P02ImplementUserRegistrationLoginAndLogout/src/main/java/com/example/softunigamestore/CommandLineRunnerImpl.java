package com.example.softunigamestore;

import com.example.softunigamestore.exceptions.UserAlreadyLoggedInException;
import com.example.softunigamestore.exceptions.UserNotLoggedInException;
import com.example.softunigamestore.exceptions.ValidationException;
import com.example.softunigamestore.services.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final ExecutorService executorService;

    @Autowired
    public CommandLineRunnerImpl(ExecutorService executorService) {
        this.executorService = executorService;
    }
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter command:");
        String command = scanner.nextLine();

        while (!command.equals("End")) {
            String result;
            try {
                result = executorService.execute(command);
            } catch (ValidationException | UserNotLoggedInException | UserAlreadyLoggedInException ex) {
                result = ex.getMessage();
            }

            System.out.println(result);
            System.out.println("Enter command:");
            command = scanner.nextLine();
        }
    }

}
