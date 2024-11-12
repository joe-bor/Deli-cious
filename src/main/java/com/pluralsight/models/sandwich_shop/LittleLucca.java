package com.pluralsight.models.sandwich_shop;

import com.pluralsight.models.sandwich.Sandwich;
import com.pluralsight.models.enums.BreadType;
import com.pluralsight.models.enums.Size;
import com.pluralsight.models.topping.MeatTopping;
import com.pluralsight.models.topping.RegularTopping;
import com.pluralsight.models.topping.SignatureTopping;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Getter
public class LittleLucca extends SandwichShop implements SpecialtyShop {

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
        Sandwich sandwich = new Sandwich(BreadType.WHITE, Size.LARGE) ;
        // add prem toppings
        List<MeatTopping> meatToppingList = List.of(
                new Mortadella(true),
                new MeatTopping("Salami", true)
        );
        List<RegularTopping> regularToppingList = new ArrayList<>();
        Stream.of("Mayo", "Mustard", "Pickles", "Red Onions", "Lettuce", "Tomato")
                .map(RegularTopping::new)
                .forEach(regularToppingList::add);

        regularToppingList.addFirst(new HotPepperSauce());
        regularToppingList.addFirst(new OGGarlicSauce());

        sandwich.addToppings(meatToppingList);
        sandwich.addToppings(regularToppingList);

        return sandwich;
    }


    private class Mortadella extends MeatTopping implements SignatureTopping {
        private Mortadella(String name, boolean extra) {
            super(name, extra);
        }

        private Mortadella(boolean extra) {
            this("Mortadella", extra);
        }
    }

    private class HotPepperSauce extends RegularTopping implements SignatureTopping {
        private HotPepperSauce(String name) {
            super(name);
        }

        private HotPepperSauce() {
            this("Hot Pepper Sauce");
        }
    }

    private class OGGarlicSauce extends RegularTopping implements SignatureTopping {
        private OGGarlicSauce(String name) {
            super(name);
        }

        private OGGarlicSauce() {
            this("OG Garlic Sauce");
        }
    }
}


