package com.pluralsight.models.topping;

import com.pluralsight.models.enums.Size;

import java.util.*;

public class MeatTopping extends PremiumTopping {
    public static final Set<String> OPTIONS = new HashSet<>() {{
        addAll(List.of("Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"));
    }};

    public MeatTopping(String name, boolean extra) {
        super(name, extra);
    }

    @Override
    public double getPriceBasedOnSize(Size size) {
        double total = 0;

        total += switch (size) {
            case SMALL -> 1.00;
            case MEDIUM -> 2.00;
            case LARGE -> 3.00;
        };

        if (this.isExtra()) {
            total += switch (size) {
                case SMALL -> .50;
                case MEDIUM -> 1.00;
                case LARGE -> 1.50;
            };
        }

        return total;
    }
}
