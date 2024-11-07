package com.pluralsight.ui;

import java.util.Scanner;

public class UserInterface {
    private static final Scanner SCANNER = new Scanner(System.in);

    public void display() {
        displayHomeScreen();
    }

    public void displayHomeScreen() {
        boolean isShown = false;

        do {
            isShown = true;
            System.out.println("""
                    
                    ------  Home Screen ------
                    [1] - New Order
                    [0] - Exit
                    
                    """);

            String option = SCANNER.nextLine();
            switch (option) {
                case "1" -> displayOrderScreen();
                case "0" -> {
                    System.out.println("Exit");
                    isShown = false;
                }
                default -> System.out.println("Invalid Option!");
            }
        } while (isShown);
    }

    private void displayOrderScreen(){
        boolean isShown = false;

        do{
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
            switch (option){
                case "1" -> System.out.println("Add Sandwich");
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
