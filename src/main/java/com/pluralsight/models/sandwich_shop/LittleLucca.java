package com.pluralsight.models.sandwich_shop;

import com.pluralsight.models.sandwich.Sandwich;
import com.pluralsight.models.enums.BreadType;
import com.pluralsight.models.enums.Size;
import com.pluralsight.models.topping.MeatTopping;
import com.pluralsight.models.topping.RegularTopping;
import lombok.Getter;

import java.util.List;

@Getter
public class LittleLucca extends SandwichShop {

    public static LittleLucca instance;
    private Sandwich signatureSandwich;


    // singleton pattern
    public static LittleLucca getInstance() {
        if (instance == null) {
            instance = new LittleLucca();
        }
        return instance;
    }

    private LittleLucca() {
        this("Little Lucca", "724 El Camino Real, South San Francisco, CA 94080");
        this.signatureSandwich = createSignatureSandwich();
    }

    private LittleLucca(String name, String address) {
        super(name, address);
    }

    private Sandwich createSignatureSandwich() {
        Sandwich sandwich = new Sandwich(BreadType.WHITE, Size.LARGE);
        // add prem toppings
        List<MeatTopping> meatToppingList = List.of("Mortadella", "Salami").stream()
                .map(string -> new MeatTopping(string, true))
                .toList();
        List<RegularTopping> regularToppingList = List.of("Mayo", "Mustard", "Pickles", "Red Onions", "Lettuce", "Tomato", "Hot Pepper Sauce", "Original Garlic Sauce").stream()
                .map(RegularTopping::new)
                .toList();

        sandwich.addToppings(meatToppingList);
        sandwich.addToppings(regularToppingList);

        return sandwich;
    }

}


