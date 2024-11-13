package com.pluralsight.models.topping;

import com.pluralsight.models.enums.Size;

import java.util.*;

public class CheeseTopping extends PremiumTopping {
    public static final Set<String> OPTIONS = new HashSet<>() {{
        addAll(List.of("American", "Provolone", "Cheddar", "Swiss"));
    }};

    public CheeseTopping(String name, boolean extra) {
        super(name, extra);
    }

    @Override
    public double getPriceBasedOnSize(Size size) {
        double total = 0;

        total += switch (size) {
            case SMALL -> 0.75;
            case MEDIUM -> 1.50;
            case LARGE -> 2.25;
        };

        if (this.isExtra()) {
            total += switch (size) {
                case SMALL -> .30;
                case MEDIUM -> .60;
                case LARGE -> .90;
            };
        }

        return total;
    }
}
