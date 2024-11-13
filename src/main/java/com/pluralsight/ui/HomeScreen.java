package com.pluralsight.ui;

import com.pluralsight.models.sandwich_shop.SandwichShop;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class HomeScreen {
    private final Scanner SCANNER;

    public void displayHomeScreen(UserInterface userInterface) {
        boolean isShown;

        do {
            displayGreeting(userInterface.getSandwichShop());

            isShown = true;
            System.out.println("""
                    
                    ------  Home Screen ------
                    [1] - New Order
                    [2] - Switch Shops
                    [0] - Exit
                    
                    """);

            String option = SCANNER.nextLine();
            switch (option) {
                case "1" -> userInterface.processNewOrder();
                case "2" -> userInterface.processSwitchShops();
                case "0" -> {
                    System.out.println("Terminating the app...");
                    isShown = false;
                }
                default -> System.out.println("Invalid Option!");
            }
        } while (isShown);
    }

    private void displayGreeting(SandwichShop sandwichShop) {
        System.out.printf("""
                =========================================================
                            Welcome to %s
                %s
                =========================================================
                """, sandwichShop.getName(), sandwichShop.getAddress());
    }
}
