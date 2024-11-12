package com.pluralsight.models.sandwich;

import com.pluralsight.models.enums.BreadType;
import com.pluralsight.models.enums.Size;
import com.pluralsight.models.topping.CheeseTopping;
import com.pluralsight.models.topping.MeatTopping;
import com.pluralsight.models.topping.RegularTopping;
import com.pluralsight.models.topping.Topping;

import java.util.ArrayList;
import java.util.List;

public class PhillyCheeseSteak extends Sandwich {
    private PhillyCheeseSteak(BreadType breadType, Size sandwichSize, List<Topping> toppings, boolean toasted) {
        super(breadType, sandwichSize, toppings, toasted);
    }

    public PhillyCheeseSteak(){
        this(BreadType.WHITE, Size.MEDIUM, createDefaultToppings(), true);
    }

    private static List<Topping> createDefaultToppings() {
        List<Topping> toppings = new ArrayList<>();

        toppings.addAll(List.of(
                new MeatTopping("Steak", false),
                new CheeseTopping("American Cheese", false)
        ));
        toppings.addAll(List.of(
                new RegularTopping("Peppers"),
                new RegularTopping("Mayo")
        ));

        return toppings;
    }
}
