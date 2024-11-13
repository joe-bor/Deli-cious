package com.pluralsight.models.sandwich;

import com.pluralsight.models.SizeDependentPricing;
import com.pluralsight.models.enums.BreadType;
import com.pluralsight.models.enums.Size;
import com.pluralsight.models.topping.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Sandwich implements SizeDependentPricing {

    private BreadType breadType;
    private Size sandwichSize;
    private List<Topping> toppings;
    private boolean toasted;

    public Sandwich(BreadType breadType, Size sandwichSize) {
        this.breadType = breadType;
        this.sandwichSize = sandwichSize;
        this.toppings = new ArrayList<>();
        this.toasted = false;
    }

    public void addTopping(Topping topping) {
        this.getToppings().add(topping);
    }

    public <T extends Topping> void addToppings(List<T> toppings) {
        for (Topping topping : toppings) {
            this.addTopping(topping);
        }
    }

    private double getBreadCost() {
        return getPriceBasedOnSize(this.getSandwichSize());
    }

    private double getToppingsCost() {
        return this.getToppings().stream()
                .mapToDouble(topping -> {
                    // check if topping is premium
                    if (topping instanceof PremiumTopping premiumTopping) {
                        // topping's implementation of getPriceBasedOnSize takes care of the 'extra' and its cost
                        return premiumTopping.getPriceBasedOnSize(this.getSandwichSize());
                    } else {
                        // topping is free if not premium
                        return 0;
                    }
                }).sum();
    }

    public double getTotalCost() {
        return getBreadCost() + getToppingsCost();
    }

    public List<Topping> getSignatureToppings() {
        return this.getToppings().stream()
                .filter(SignatureTopping.class::isInstance)
                .toList();
    }

    @Override
    public double getPriceBasedOnSize(Size size) {
        return switch (size) {
            case SMALL -> 5.50;
            case MEDIUM -> 7.0;
            case LARGE -> 8.50;
        };
    }

    @Override
    public String toString() {
        String meatToppings = toppings.stream()
                .filter(topping -> topping instanceof MeatTopping)
                .map(topping -> {
                    MeatTopping meat = (MeatTopping) topping;
                    return meat.isExtra() ? "Extra " + meat.getName() : meat.getName();
                })
                .collect(Collectors.joining(", "));

        String cheeseToppings = toppings.stream()
                .filter(topping -> topping instanceof CheeseTopping)
                .map(topping -> {
                    CheeseTopping cheese = (CheeseTopping) topping;
                    return cheese.isExtra() ? "Extra " + cheese.getName() : cheese.getName();
                })
                .collect(Collectors.joining(", "));

        String otherToppings = toppings.stream()
                .filter(topping -> topping instanceof RegularTopping)
                .map(Topping::getName)
                .collect(Collectors.joining(", "));

        String specialToppings = toppings.stream()
                .filter(topping -> topping instanceof SignatureTopping)
                .map(Topping::getName)
                .collect(Collectors.joining(", "));

        return String.format("""
                ---------------------------------------------------------------------
                | Bread: %-15s | Size: %-15s | Toasted: %-15s
                ---------------------------------------------------------------------
                | Meat: %-40s
                | Cheese: %-40s
                | Others: %-40s
                | Unique: %-40s
                ---------------------------------------------------------------------
                """, breadType, sandwichSize, toasted ? "Yes" : "No", meatToppings, cheeseToppings, otherToppings, specialToppings);
    }
}
