package com.pluralsight.ui;

import com.pluralsight.models.*;
import com.pluralsight.models.enums.BreadType;
import com.pluralsight.models.enums.Size;
import com.pluralsight.models.sandwich.Sandwich;
import com.pluralsight.models.sandwich_shop.*;
import com.pluralsight.models.topping.*;
import lombok.Data;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Data
public class UserInterface {
    private static final Scanner SCANNER = new Scanner(System.in);
    private SandwichShop store = initStore();
    private Order order;

    public void display() {
        this.store = pickStore();
        displayGreeting();
    }

    private void displayGreeting() {
        System.out.printf("""
                =========================================================
                            Welcome to %s
                %s
                =========================================================
                """, this.store.getName(), this.store.getAddress());
        displayHomeScreen();
    }

    public void displayHomeScreen() {
        boolean isShown;

        do {
            isShown = true;
            System.out.println("""
                    
                    ------  Home Screen ------
                    [1] - New Order
                    [2] - Switch Shops
                    [0] - Exit
                    
                    """);

            String option = SCANNER.nextLine();
            switch (option) {
                case "1" -> processNewOrder();
                case "2" -> this.setStore(pickStore());
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
            System.out.printf("""
                    
                    ------ Order Screen ------
                    %s
                    [1] - Add Sandwich
                    [2] - Add Drink
                    [3] - Add Chips
                    [4] - Checkout
                    [0] - Cancel Order
                    """, switch (this.store) {
                case SpecialtyShop ignored -> "[S] - ** Signature Sandwich **";
                case BonusShop ignored -> "[S] - Template Sandwiches";
                default -> "";
            });

            String option = SCANNER.nextLine();
            switch (option) {
                case "1" -> processAddSandwichToOrder();
                case "2" -> processAddDrinkToOrder();
                case "3" -> processAddChipToOrder();
                case "4" -> {
                    displayCheckoutScreen();
                    isShown = false;
                }
                case "0" -> {
                    processCancelOrder();
                    isShown = false;
                }
                case "S" -> processSignatureSandwich(this.store);
                case "test" -> System.out.println(this.order); //TODO: REMOVE
                default -> System.out.println("Invalid Option!");
            }
        } while (isShown);
    }

    private void processSignatureSandwich(SandwichShop sandwichShop) {
        Sandwich selectedSandwich = switch (sandwichShop) {
            case SpecialtyShop specialtyShop -> specialtyShop.getSignatureSandwich();
            case BonusShop bonusShop -> {
                System.out.println("Pick the sandwich you want to use as template? ");
                for (Sandwich sandwich : bonusShop.getTemplateSandwiches()) {
                    System.out.println(sandwich);
                }
                System.out.println("""
                        [1] - BLT
                        [2] - Philly Cheese Steak
                        [0] - Exit
                        """);

                String templateChoice = SCANNER.nextLine();
                yield switch (templateChoice) {
                    case "1" -> bonusShop.getTemplateSandwiches().get(0);
                    case "2" -> bonusShop.getTemplateSandwiches().get(1);
                    default -> {
                        System.out.println("Invalid Option!");
                        yield null;
                    }
                };
            }
            default -> {
                System.out.printf("%s does not have a customizable sandwich\n", sandwichShop.getName());
                yield null;
            }
        };

        System.out.println("Here is the sandwich you selected: ");
        System.out.println(selectedSandwich);

        System.out.println("""
                What would you like to do
                [1] - Add sandwich to current order
                [2] - Customize sandwich
                """);

        String answer = SCANNER.nextLine();
        switch (answer) {
            case "1" -> this.order.addSandwich(selectedSandwich);
            case "2" -> customizeSignatureSandwich(selectedSandwich);
            default -> System.out.println("Invalid Option!");
        }
    }

    private void customizeSignatureSandwich(Sandwich sandwich) {
        boolean isCustomizing;

        do {
            isCustomizing = true;
            // show current state of sig sandwich
            System.out.println(sandwich);

            // get unique toppings (if any)
            String formattedUniqueToppings = sandwich.getSignatureToppings().stream()
                    .map(Topping::getName)
                    .collect(Collectors.joining(", "));

            if (!formattedUniqueToppings.isBlank()) {
                System.out.println("The following are unique to this sandwich and can't be modified: ");
                System.out.println(formattedUniqueToppings);
            }

            System.out.println("""
                    
                    What would you like to customize?
                    [1] - Bread Type
                    [2] - Size
                    [3] - Toasted
                    [4] - Toppings
                    [5] - Done customizing
                    """);
            String answer = SCANNER.nextLine();

            switch (answer) {
                case "1" -> sandwich.setBreadType(selectBread());
                case "2" -> sandwich.setSandwichSize(selectSandwichSize());
                case "3" -> sandwich.setToasted(selectToasted());
                case "4" -> customizeSignatureToppings(sandwich);
                case "5" -> {
                    this.order.addSandwich(sandwich);
                    isCustomizing = false;
                }
                default -> System.out.println("Invalid option!");
            }
        } while (isCustomizing);
    }

    private void customizeSignatureToppings(Sandwich signatureSandwich) {
        // extract signature toppings (if any)
        List<Topping> signatureToppings = signatureSandwich.getSignatureToppings();
        // do toppings selection (regular way)
        signatureSandwich.setToppings(selectToppings());
        // re-add signature toppings extracted from first line
        signatureSandwich.addToppings(signatureToppings);
    }


    private void displayCheckoutScreen() {
        // Display current order and show options
        System.out.printf("""
                
                %s
                
                ------ Checkout Screen ------
                [1] - Confirm Order
                [2] - Cancel Order
                
                """, this.order);

        String answer = SCANNER.nextLine().trim();
        switch (answer) {
            case "1" -> processCheckout();
            case "2" -> processCancelOrder();
            default -> System.out.println("Invalid option");
        }
    }

    private void processCheckout() {
        System.out.println("Confirming Order...");
        // Create the receipt
        Receipt receipt = new Receipt(this.order);
        ReceiptFileManager.saveReceipt(this.store, receipt);
        // set this.order to null
        this.setOrder(null);

        System.out.println("Here's your receipt, thank you!");

    }

    private void processAddChipToOrder() {
        System.out.print("What kind of chips would you like? ");
        String chipsName = SCANNER.nextLine();

        Chip newChip = new Chip(chipsName);
        this.order.addChip(newChip);
        System.out.printf("Added %s (chips) to the order\n", newChip);
    }

    private void processAddDrinkToOrder() {
        System.out.print("What flavor of drink do you want? ");
        String drinkFlavor = SCANNER.nextLine();
        System.out.print("What size? (Small/Medium/Large) ");
        String drinkSize = SCANNER.nextLine().trim().toUpperCase();

        Drink newDrink;
        try {
            newDrink = new Drink(drinkFlavor, Size.valueOf(drinkSize));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid drink size. Defaulted to 'SMALL'");
            newDrink = new Drink(drinkFlavor, Size.SMALL);
        }

        System.out.printf("Added a %s drink to the order\n", newDrink);
        this.getOrder().addDrink(newDrink);
    }

    private void processCancelOrder() {
        System.out.println("Cancelling order...");
        System.out.println("Going back to home screen...");
        this.setOrder(null);
    }

    private void processAddSandwichToOrder() {
        boolean isShown;

        do {
            isShown = true;
            createSandwichScreen();

            // prompt to make another sandwich?
            System.out.println("Would you like to add more sandwich? (y/n)");
            String answer = SCANNER.nextLine();

            if (answer.equalsIgnoreCase("n")) {
                isShown = false;
            }
        } while (isShown);
    }

    private void createSandwichScreen() {
        System.out.println("------- Sandwich Creation Screen ------");
        Sandwich currentSandwich;

        // create a sandwich (just bread and size)
        BreadType breadType = selectBread();
        Size sandwichSize = selectSandwichSize();
        currentSandwich = new Sandwich(breadType, sandwichSize);

        // add toppings to the sandwich
        List<Topping> toppings = selectToppings();
        currentSandwich.addToppings(toppings);

        // alter toasted value of sandwich
        boolean toasted = selectToasted();
        currentSandwich.setToasted(toasted);

        // print out to customers the sandwich they just created
        System.out.println("\nSandwich created!");
        System.out.println(currentSandwich);
        // add this sandwich to running order
        this.order.addSandwich(currentSandwich);

        // show current order
        System.out.println(this.order);
    }

    private boolean selectToasted() {
        System.out.println("Would you like your sandwich toasted? (y/n) ");
        String toasted = SCANNER.nextLine();
        return toasted.equalsIgnoreCase("y");
    }

    private Size selectSandwichSize() {
        System.out.println("\nSelect your sandwich size:");
        for (Size sizeVal : Size.values()) {
            System.out.printf(" - %s (%s)\n", sizeVal, sizeVal.getInOfSandwich());
        }
        String size = SCANNER.nextLine().trim().toUpperCase();
        try {
            return Size.valueOf(size);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid size. Defaulting to 'Small'");
            return Size.SMALL;
        }
    }

    private BreadType selectBread() {
        System.out.println("Select your bread:");
        Arrays.stream(BreadType.values()).forEach(breadType -> System.out.printf(" - %s\n", breadType.name()));

        try {
            return BreadType.valueOf(SCANNER.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid bread type. Defaulting to 'White'");
            return BreadType.WHITE;
        }
    }

    private List<Topping> selectToppings() {
        List<Topping> toppings = new ArrayList<>();

        System.out.println("""
                
                Instructions:
                    - Choose your toppings
                    - You can select multiple values from the options by separating them with commas.
                    - eg. Ham,Steak,Bacon
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

        System.out.println("\nHere are the options for sides: ");
        RegularTopping.SIDES.forEach(s -> System.out.println("    - " + s));

        String answer = SCANNER.nextLine();
        if (answer.isBlank()) return sides;

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

        System.out.println("\nHere are the options for sauces: ");
        RegularTopping.SAUCES.forEach(s -> System.out.println("    - " + s));

        String answer = SCANNER.nextLine();
        if (answer.isBlank()) return sauces;

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

    private List<RegularTopping> selectRegularToppings() {
        List<RegularTopping> regularToppings = new ArrayList<>();

        System.out.println("\n** Regular Toppings **: ");
        System.out.println(" Type 'All' for all toppings or keep it 'Blank' if you want none");
        RegularTopping.OPTIONS.forEach(s -> System.out.println("    - " + s));

        String answer = SCANNER.nextLine();
        if (answer.isBlank()) return regularToppings;
        if (answer.equalsIgnoreCase("all")) {
            return RegularTopping.OPTIONS.stream()
                    .map(RegularTopping::new)
                    .toList();
        }

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
        MeatTopping.OPTIONS.forEach(s -> System.out.println("  - " + s));

        String answer = SCANNER.nextLine().trim();
        String[] meatArr = answer.split(Pattern.quote(","));
        for (String meat : meatArr) {
            if (MeatTopping.OPTIONS.contains(meat.trim())) {
                System.out.print("Would you like extra for " + meat + "? (y/n) ");
                String extraMeat = SCANNER.nextLine();

                boolean extra = extraMeat.equalsIgnoreCase("y");
                var topping = new MeatTopping(meat, extra);
                meatToppings.add(topping);
            } else {
                System.out.printf("Sorry, we do not carry %s\n", meat);
            }
        }
        return meatToppings;
    }

    private List<Topping> selectCheese() {
        List<Topping> cheeseToppings = new ArrayList<>();

        System.out.println("\nHere are the options for cheese: ");
        CheeseTopping.OPTIONS.forEach(s -> System.out.println("  - " + s));

        String answer = SCANNER.nextLine().trim();
        String[] cheeseArr = answer.split(Pattern.quote(","));
        for (String cheese : cheeseArr) {
            if (CheeseTopping.OPTIONS.contains(cheese.trim())) {
                System.out.print("Would you like extra for " + cheese + "? (y/n) ");
                String extraCheese = SCANNER.nextLine();

                boolean extra = extraCheese.equalsIgnoreCase("y");
                var topping = new CheeseTopping(cheese, extra);
                cheeseToppings.add(topping);
            } else {
                System.out.printf("Sorry, we do not carry %s\n", cheese);
            }
        }
        return cheeseToppings;
    }

    private SandwichShop initStore() {
        return new SandwichShop("Deli-Store", "123 Main St");
    }

    private SandwichShop pickStore() {
        System.out.println("Where do you want to get a sandwich?");
        // list Stores
        SandwichShopList.printStoreList();

        String answer = SCANNER.nextLine();
        return switch (answer) {
            case "1" -> SandwichShopList.getSandwichShops().get(0); // LittleLucca.getInstance();
            case "2" -> SandwichShopList.getSandwichShops().get(1); //new BonusShop();
            default -> new SandwichShop("Default-Deli-Store", "123 Main St");
        };
    }
}
