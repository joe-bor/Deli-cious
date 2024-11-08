package com.pluralsight.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Sandwich {
    private String breadType;
    private String sandwichSize;
    private List<Topping> toppings;
    private boolean toasted;

    @Override
    public String toString() {
        String formattedToppings = toppings.stream().map(Topping::getName).collect(Collectors.joining(", "));

        return String.format("""
                ---------------------------------------------------------------------
                | Bread: %-10s | Size: %-10s | Toasted: %-10s
                ---------------------------------------------------------------------
                | Toppings: %-50s
                ---------------------------------------------------------------------
                """, breadType, sandwichSize, toasted ? "Yes" : "No", formattedToppings);
    }
}
