package com.pluralsight.models.sandwich_shop;

import com.pluralsight.models.sandwich.BLT;
import com.pluralsight.models.sandwich.PhillyCheeseSteak;
import com.pluralsight.models.sandwich.Sandwich;

import java.util.List;

public class BonusShop extends SandwichShop {

    private final List<Sandwich> TEMPLATE_SANDWICHES = List.of(
            new BLT(),
            new PhillyCheeseSteak()
    );

    private BonusShop(String name, String address) {
        super(name, address);
    }

    public BonusShop() {
        this("Bonus Challenge", "Contains 'template sandwiches' that can be customized");
    }

    public List<Sandwich> getTemplateSandwiches(){
        return TEMPLATE_SANDWICHES;
    }
}
