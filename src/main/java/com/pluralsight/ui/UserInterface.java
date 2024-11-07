package com.pluralsight.ui;

import com.pluralsight.models.PremiumTopping;
import com.pluralsight.models.Sandwich;
import com.pluralsight.models.Topping;

import java.util.*;

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

    private void displayOrderScreen() {
        boolean isShown = false;

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
                case "1" -> displaySandwichScreen();
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

    private void displaySandwichScreen() {
        boolean isShown = false;

        do {
            isShown = true;

            System.out.println("------- Sandwich Creation Screen ------");
            String breadType = selectBread();
            String sandwichSize = selectSandwichSize();
            List<Topping> toppings = selectToppings();
            boolean toasted = selectToasted();

            Sandwich sandwich =  new Sandwich(breadType, sandwichSize, toppings, toasted);
            System.out.println("sandwich = " + sandwich);

        } while (isShown);
    }

    private boolean selectToasted() {
        System.out.println("Would you like your sandwich toasted? (y/n) ");
        String toasted = SCANNER.nextLine();
        return toasted.equalsIgnoreCase("y") ? true : false;
    }

    private String selectSandwichSize() {
        System.out.println("""
                Select your sandwich size:
                    - 4
                    - 8
                    - 12
                """);
        return SCANNER.nextLine();
    }

    private String selectBread() {
        System.out.println("""
                Select your bread:
                    - White
                    - Wheat
                    - Rye
                    - Wrap
                """);
        return SCANNER.nextLine();
    }

    private List<Topping> selectToppings(){
        List<Topping> toppings= new ArrayList<>();

        System.out.println("""
                    Toppings:
                        - Meat
                        - Cheese
                        - Other toppings:
                        - Select sauces:
                    """);

        System.out.println("** Premium Toppings **");
        System.out.println("* Meat *");
        List<String> meatToppings = List.of("Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon");

        System.out.println("Keep it BLANK if you don't want to add a topping");
        for (String meat : meatToppings) {
            System.out.println("Would you like to add " + meat + " to your sandwich? ");
            String addMeat = SCANNER.nextLine();
            String extraMeat = null;

            if (!addMeat.isBlank()) {
                System.out.println("Would you like extra?");
                extraMeat = SCANNER.nextLine();

                boolean extra = extraMeat.isBlank() ? false : true;
                var topping = new PremiumTopping(meat, extra, "Meat");
                System.out.println(topping);

                toppings.add(topping);
            }

        }
        System.out.println(toppings);
        return toppings;
    }
}
