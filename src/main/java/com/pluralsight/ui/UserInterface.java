package com.pluralsight.ui;

import com.pluralsight.models.PremiumTopping;
import com.pluralsight.models.Sandwich;
import com.pluralsight.models.Store;
import com.pluralsight.models.Topping;

import java.util.*;
import java.util.regex.Pattern;

public class UserInterface {
    private static final Scanner SCANNER = new Scanner(System.in);
    private Store store;

    public void display() {
        displayHomeScreen();
    }

    public void displayHomeScreen() {
        boolean isShown;

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
        boolean isShown;

        do {
            isShown = true;

            System.out.println("------- Sandwich Creation Screen ------");
            String breadType = selectBread();
            String sandwichSize = selectSandwichSize();
            List<Topping> toppings = selectToppings();
            boolean toasted = selectToasted();

            Sandwich sandwich = createSandwich(breadType, sandwichSize, toppings, toasted);
        } while (isShown);
    }

    private Sandwich createSandwich(String breadType, String sandwichSize, List<Topping> toppings, boolean toasted) {
        System.out.println("Sandwich created!");
        Sandwich sandwich = new Sandwich(breadType, sandwichSize, toppings, toasted);
        System.out.println(sandwich);
        return sandwich;
    }

    private boolean selectToasted() {
        System.out.println("Would you like your sandwich toasted? (y/n) ");
        String toasted = SCANNER.nextLine();
        return toasted.equalsIgnoreCase("y");
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

    private List<Topping> selectToppings() {
        List<Topping> toppings = new ArrayList<>();

        System.out.println("""
                Toppings:
                    - Meat
                    - Cheese
                    - Other toppings:
                    - Select sauces:
                """);

        List<Topping> meatToppings = selectMeat();
        List<Topping> cheeseToppings = selectCheese();
        toppings.addAll(meatToppings);
        toppings.addAll(cheeseToppings);
        return toppings;
    }

    private List<Topping> selectMeat() {
        List<Topping> meatToppings = new ArrayList<>();

        System.out.println("** Premium Toppings **");
        System.out.println("Here are the options for meat: ");
        PremiumTopping.MEAT_OPTIONS.forEach(s -> System.out.println("  - " + s));

        String answer = SCANNER.nextLine().trim();
        String[] meatArr = answer.split(Pattern.quote(","));
        for (String meat : meatArr) {
            if (PremiumTopping.MEAT_OPTIONS.contains(meat.trim())) {
                System.out.print("Would you like extra for " + meat + " ? (y/n)");
                String extraMeat = SCANNER.nextLine();

                boolean extra = extraMeat.equalsIgnoreCase("y");
                var topping = new PremiumTopping(meat, extra, "Meat");
                meatToppings.add(topping);
            } else {
                System.out.printf("Sorry, we do not carry %s\n", meat);
            }
        }
        return meatToppings;
    }

    private List<Topping> selectCheese() {
        List<Topping> cheeseToppings = new ArrayList<>();

        System.out.println("Here are the options for cheese: ");
        PremiumTopping.CHEESE_OPTIONS.forEach(s -> System.out.println("  - " + s));

        String answer = SCANNER.nextLine().trim();
        String[] cheeseArr = answer.split(Pattern.quote(","));
        for (String cheese : cheeseArr) {
            if (PremiumTopping.MEAT_OPTIONS.contains(cheese.trim())) {
                System.out.print("Would you like extra for " + cheese + " ? (y/n)");
                String extraCheese = SCANNER.nextLine();

                boolean extra = extraCheese.equalsIgnoreCase("y");
                var topping = new PremiumTopping(cheese, extra, "Cheese");
                cheeseToppings.add(topping);
            } else {
                System.out.printf("Sorry, we do not carry %s\n", cheese);
            }
        }
        return cheeseToppings;
    }
}
