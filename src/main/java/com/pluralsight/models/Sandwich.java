package com.pluralsight.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Sandwich {
    private String breadType;
    private String sandwichSize;
    private List<Topping> toppings;
    private boolean toasted;

    public Sandwich(String breadType, String sandwichSize){
         this.breadType = breadType;
         this.sandwichSize = sandwichSize;
         this.toppings = new ArrayList<>();
         this.toasted = false;
    }

    public void addTopping(Topping topping){
        this.getToppings().add(topping);
    }

    public void addToppings(List<Topping> toppings){
        for (Topping topping : toppings) {
            this.getToppings().add(topping);
        }
    }

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
