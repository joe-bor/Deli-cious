package com.pluralsight.ui;

import com.pluralsight.models.Order;
import com.pluralsight.models.sandwich_shop.SandwichShop;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class OrderScreen {
    private final Scanner SCANNER;

    public void displayOrderScreen(UserInterface userInterface, Order order, SandwichShop sandwichShop) {
        boolean isShown;

        do {
            isShown = true;
            System.out.println("""
                    
                    ------ Order Screen ------
                    [1] - Add Sandwich
                    [2] - Add Drink
                    [3] - Add Chips
                    [4] - Checkout
                    [0] - Cancel Order
                    """);

            String option = SCANNER.nextLine();
            switch (option) {
                case "1" -> userInterface.processAddSandwichToOrder();
                case "2" -> userInterface.processAddDrinkToOrder();
                case "3" -> userInterface.processAddChipToOrder();
                case "4" -> {
                    userInterface.processCheckout();
                    isShown = false;
                }
                case "0" -> {
                    userInterface.processCancelOrder();
                    isShown = false;
                }
                case "test" -> System.out.println(order);
                default -> System.out.println("Invalid Option!");
            }
        } while (isShown);
    }
}

