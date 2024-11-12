package com.pluralsight.models.sandwich;


import com.pluralsight.models.enums.BreadType;
import com.pluralsight.models.enums.Size;
import com.pluralsight.models.topping.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BLT extends Sandwich {

    private static List<Topping> createDefaultToppings() {
        List<Topping> toppings = new ArrayList<>();

        toppings.addAll(List.of(
                new MeatTopping("Bacon", false),
                new CheeseTopping("Cheddar", false))
        );

        toppings.addAll(Arrays.stream("Lettuce,Tomato,Ranch".split(","))
                .map(RegularTopping::new)
                .toList());

        return toppings;
    }


    private BLT(BreadType breadType, Size sandwichSize, List<Topping> toppings, boolean toasted) {
        super(breadType, sandwichSize, toppings, toasted);
    }

    public BLT() {
        this(BreadType.WHITE, Size.MEDIUM, createDefaultToppings(), true);
    }
}
