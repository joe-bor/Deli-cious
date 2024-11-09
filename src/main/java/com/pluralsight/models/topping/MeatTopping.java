package com.pluralsight.models.topping;

import java.util.*;

public class MeatTopping extends PremiumTopping {
    private static final Map<String, Double> SIZE_TO_PRICE = new HashMap<>() {{
        put("4", 1.00);
        put("8", 2.00);
        put("12", 3.00);
    }};
    private static final Map<String, Double> SIZE_TO_EXTRA_PRICE = new HashMap<>() {{
        put("4", .50);
        put("8", 1.00);
        put("12", 1.50);
    }};
    public static final Set<String> OPTIONS = new HashSet<>() {{
        addAll(List.of("Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"));
    }};

    public MeatTopping(String name, boolean extra) {
        super(name, extra);
    }

    @Override
    public double getPriceBasedOn(String size) {
        double total = 0;
        total += SIZE_TO_PRICE.getOrDefault(size, 2.0);
        total += SIZE_TO_EXTRA_PRICE.getOrDefault(size, 2.0);

        return total;
    }
}
