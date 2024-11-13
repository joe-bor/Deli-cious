package com.pluralsight.ui;

import com.pluralsight.models.Order;
import com.pluralsight.models.Receipt;
import com.pluralsight.models.ReceiptFileManager;
import com.pluralsight.models.sandwich_shop.SandwichShop;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class CheckoutScreen {
    private final Scanner SCANNER;

    public void displayCheckoutScreen(Order order, SandwichShop sandwichShop) {
        System.out.printf("""
                
                %s
                
                ------ Checkout Screen ------
                [1] - Confirm Order
                [2] - Cancel Order
                
                """, order);

        String answer = SCANNER.nextLine().trim();
        switch (answer) {
            case "1" -> confirmOrder(order, sandwichShop);
            case "2" -> cancelOrder(order);
            default -> System.out.println("Invalid option");
        }
    }

    private void confirmOrder(Order order, SandwichShop sandwichShop) {
        System.out.println("Confirming Order...");
        Receipt receipt = new Receipt(order);
        sandwichShop.addReceipt(receipt);
        ReceiptFileManager.saveReceipt(sandwichShop, receipt);
        order = null;
        System.out.println("Here's your receipt, thank you!");
    }

    private void cancelOrder(Order order) {
        order = null;
        System.out.println("Order canceled. Returning to main menu...");
    }
}
