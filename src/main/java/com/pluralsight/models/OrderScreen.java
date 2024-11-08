package com.pluralsight.models;

import lombok.Data;

import java.util.Scanner;

@Data
public class OrderScreen {
    private static boolean isShown;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void display(){
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
//                    case "1" -> displaySandwichScreen();
                    case "2" -> System.out.println("Add Drink");
                    case "3" -> System.out.println("Add Chips");
                    case "4" -> System.out.println("Checkout");
                    case "0" -> {
                        System.out.println("Cancel Order & Go back");
                        isShown = false;
                    }
                    default -> System.out.println("Invalid Option!");
                }
            } while (isShown);
    }
}
