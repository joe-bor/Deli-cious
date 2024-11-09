package com.pluralsight.models;

import com.pluralsight.models.enums.BreadType;
import com.pluralsight.models.enums.Size;
import com.pluralsight.models.topping.PremiumTopping;
import com.pluralsight.models.topping.Topping;
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

    public void addToppings(List<Topping> toppings) {
        for (Topping topping : toppings) {
            this.addTopping(topping);
        }
    }

//    private double getBreadCost(){
//        return getPriceBasedOnSize(this.getSandwichSize());
//    }
//
//    private double getToppingsCost(){
//        this.getToppings().stream()
//                .mapToDouble(topping -> {
//                    if (topping instanceof PremiumTopping){
//                        yield ((PremiumTopping) topping).getPriceBasedOnSize()
//                    }
//                })
//    }

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
        String formattedToppings = toppings.stream()
                .map(topping -> {
                    if (topping instanceof PremiumTopping premiumTopping && premiumTopping.isExtra()) {
                        return "Extra " + premiumTopping.getName();
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
