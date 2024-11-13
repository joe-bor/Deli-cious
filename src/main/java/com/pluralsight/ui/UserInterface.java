package com.pluralsight.ui;

import com.pluralsight.models.*;
import com.pluralsight.models.enums.Size;
import com.pluralsight.models.sandwich_shop.*;
import lombok.Data;

import java.util.*;

@Data
public class UserInterface {
    private static final Scanner SCANNER = new Scanner(System.in);
    private final HomeScreen HOME_SCREEN = new HomeScreen(SCANNER);
    private final OrderScreen ORDER_SCREEN = new OrderScreen(SCANNER);
    private final SandwichCreationScreen SANDWICH_CREATION_SCREEN = new SandwichCreationScreen(SCANNER);
    private final CheckoutScreen CHECKOUT_SCREEN = new CheckoutScreen(SCANNER);

    private SandwichShop sandwichShop;
    private Order order;

    public void display() {
        this.sandwichShop = pickSandwichShop();
        HOME_SCREEN.displayHomeScreen(this);
    }

    void processNewOrder() {
        this.order = new Order();
        ORDER_SCREEN.displayOrderScreen(this, this.order, this.sandwichShop);
    }

    void processSwitchShops() {
        this.sandwichShop = pickSandwichShop();
    }

    void processAddSandwichToOrder() {
        SANDWICH_CREATION_SCREEN.displaySandwichCreationScreen(this.order, this.sandwichShop);
    }

    void processAddDrinkToOrder() {
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

    void processAddChipToOrder() {
        System.out.print("What kind of chips would you like? ");
        String chipsName = SCANNER.nextLine();

        Chip newChip = new Chip(chipsName);
        this.order.addChip(newChip);
        System.out.printf("Added %s (chips) to the order\n", newChip);
    }

    void processCheckout() {
        CHECKOUT_SCREEN.displayCheckoutScreen(this.order, this.sandwichShop);
    }

    void processCancelOrder() {
        System.out.println("Cancelling order...");
        System.out.println("Going back to home screen...");
        this.setOrder(null);
    }

    private SandwichShop pickSandwichShop() {
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
