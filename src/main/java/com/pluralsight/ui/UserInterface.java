package com.pluralsight.ui;

import com.pluralsight.models.*;
import lombok.Data;

import java.util.*;
import java.util.regex.Pattern;

@Data
public class UserInterface {
    private static final Scanner SCANNER = new Scanner(System.in);
    private Store store = initStore();
    private Order order;
    private Sandwich currentSandwich;

    public void display() {
        System.out.println(String.format("""
                ========================================
                            Welcome to %s
                            %s
                ========================================
                
                """, this.store.getName(), this.store.getAddress()));

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
                case "1" -> processNewOrder();
                case "0" -> {
                    System.out.println("Exit");
                    isShown = false;
                }
                default -> System.out.println("Invalid Option!");
            }
        } while (isShown);
    }

    private void processNewOrder() {
        this.order = new Order();
        displayOrderScreen();
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
                case "2" -> processAddDrinkToOrder();
                case "3" -> processAddChipToOrder();
                case "4" -> System.out.println("Checkout");
                case "0" -> {
                    processCancelOrder();
                    isShown = false;
                }
                case "test" -> System.out.println(this.order); //TODO: REMOVE
                default -> System.out.println("Invalid Option!");
            }
        } while (isShown);
    }

    private void processAddChipToOrder() {
        System.out.print("What kind of chips would you like? ");
        String chipsName = SCANNER.nextLine();

        Chip newChip = new Chip(chipsName);
        this.order.addChip(newChip);
        System.out.printf("Added %s (chips) to the order", newChip);
    }

    private void processAddDrinkToOrder() {
        System.out.print("What flavor of drink do you want? ");
        String drinkFlavor = SCANNER.nextLine();
        System.out.print("What size? (Small/Medium/Large) ");
        String drinkSize = SCANNER.nextLine();

        Drink newDrink = switch (drinkSize){
            case "Small" -> new Drink(drinkFlavor,"Small");
            case "Medium" -> new Drink(drinkFlavor,"Medium");
            case "Large" -> new Drink(drinkFlavor,"Large");
            default -> {
                System.out.println("Invalid option! Pick between Small/Medium/Large");
                yield null;
            }
        };
        if (newDrink != null) {
            System.out.printf("Added a %s drink to the order", newDrink);
            this.getOrder().addDrink(newDrink);
        }
    }

    private void processCancelOrder() {
        System.out.println("Cancelling order...");
        System.out.println("Going back to home screen...");
        this.setOrder(null);
    }

    private void displaySandwichScreen() {
        boolean isShown;

        do {
            isShown = true;
            createSandwich();

            // prompt to make another sandwich?
            System.out.println("Would you like to add more sandwich? (y/n)");
            String answer = SCANNER.nextLine();

            if (answer.equalsIgnoreCase("n")) {
                isShown = false;
            }
        } while (isShown);
    }

    private void createSandwich() {
        System.out.println("------- Sandwich Creation Screen ------");
        // create a sandwich (just bread and size)
        String breadType = selectBread();
        String sandwichSize = selectSandwichSize();
        currentSandwich = new Sandwich(breadType, sandwichSize);

        // add toppings to the sandwich
        List<Topping> toppings = selectToppings();
        currentSandwich.addToppings(toppings);

        // alter toasted value of sandwich
        boolean toasted = selectToasted();
        currentSandwich.setToasted(toasted);

        if (currentSandwich != null) {
            // print out to customers the sandwich they just created
            System.out.println("\nSandwich created!");
            System.out.println(currentSandwich);
            // add this sandwich to running order
            this.order.addSandwich(currentSandwich);

            // show current order
            System.out.println(this.order);

            // reset sandwich since customers might add more
            this.setCurrentSandwich(null);
        }
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
                    - Other toppings
                    - Sauces
                    - Sides
                """);

        toppings.addAll(selectMeat());
        toppings.addAll(selectCheese());
        toppings.addAll(selectRegularToppings());
        toppings.addAll(selectSauces());
        toppings.addAll(selectSides());

        return toppings;
    }

    private List<Topping> selectSides() {
        List<Topping> sides = new ArrayList<>();

        System.out.println("Here are the options for sides: ");
        RegularTopping.SIDES.forEach(s -> System.out.println("    - " + s));

        String answer = SCANNER.nextLine();
        String[] sidesArr = answer.split(Pattern.quote(","));
        for (String side : sidesArr) {
            if (RegularTopping.SIDES.contains(side.trim())) {
                var topping = new RegularTopping(side);
                sides.add(topping);
            } else {
                System.out.printf("Sorry, we do not carry %s\n", side);
            }
        }
        return sides;
    }

    private List<Topping> selectSauces() {
        List<Topping> sauces = new ArrayList<>();

        System.out.println("Here are the options for sauces: ");
        RegularTopping.SAUCES.forEach(s -> System.out.println("    - " + s));

        String answer = SCANNER.nextLine();
        String[] saucesArr = answer.split(Pattern.quote(","));
        for (String sauce : saucesArr) {
            if (RegularTopping.SAUCES.contains(sauce.trim())) {
                var topping = new RegularTopping(sauce);
                sauces.add(topping);
            } else {
                System.out.printf("Sorry, we do not carry %s\n", sauce);
            }
        }
        return sauces;
    }

    private List<Topping> selectRegularToppings() {
        List<Topping> regularToppings = new ArrayList<>();

        System.out.println("Here are the options for regular toppings: ");
        RegularTopping.OPTIONS.forEach(s -> System.out.println("    - " + s));

        String answer = SCANNER.nextLine();
        String[] regularArr = answer.split(Pattern.quote(","));
        for (String regularTopping : regularArr) {
            if (RegularTopping.OPTIONS.contains(regularTopping.trim())) {
                var topping = new RegularTopping(regularTopping);
                regularToppings.add(topping);
            } else {
                System.out.printf("Sorry, we do not carry %s\n", regularTopping);
            }
        }
        return regularToppings;
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
            if (PremiumTopping.CHEESE_OPTIONS.contains(cheese.trim())) {
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

    private Store initStore() {
        return new Store("Deli Store", "123 Main St");
    }
}
