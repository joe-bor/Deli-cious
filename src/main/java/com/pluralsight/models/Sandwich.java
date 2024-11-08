package com.pluralsight.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Sandwich {
    public static final Map<String, Double> SIZE_TO_PRICE = new HashMap<>() {{
        put("4", 5.50);
        put("8", 7.0);
        put("12", 8.50);
    }};

    private String breadType;
    private String sandwichSize;
    private List<Topping> toppings;
    private boolean toasted;

    public Sandwich(String breadType, String sandwichSize) {
        this.breadType = breadType;
        this.sandwichSize = sandwichSize;
        this.toppings = new ArrayList<>();
        this.toasted = false;
    }

    public void addTopping(Topping topping) {
        this.getToppings().add(topping);
    }

    public void addToppings(List<Topping> toppings) {
        for (Topping topping : toppings) {
            this.addTopping(topping);
        }
    }

    @Override
    public String toString() {
        String formattedToppings = toppings.stream()
                .map(topping -> {
                    if (topping instanceof PremiumTopping premiumTopping && premiumTopping.isExtra()) {
                        return "extra " + premiumTopping.getName();
                    }
                    return topping.getName();
                })
                .collect(Collectors.joining(", "));

        return String.format("""
                ---------------------------------------------------------------------
                | Bread: %-10s | Size: %-10s | Toasted: %-10s
                ---------------------------------------------------------------------
                | Toppings: %-50s
                ---------------------------------------------------------------------
                """, breadType, sandwichSize, toasted ? "Yes" : "No", formattedToppings);
    }
}
