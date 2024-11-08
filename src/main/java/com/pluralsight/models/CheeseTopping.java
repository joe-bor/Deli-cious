package com.pluralsight.models;

import java.util.*;

public class CheeseTopping extends PremiumTopping {
    private static final Map<String, Double> SIZE_TO_PRICE = new HashMap<>() {{
        put("4", 0.75);
        put("8", 1.50);
        put("12", 2.25);
    }};
    private static final Map<String, Double> SIZE_TO_EXTRA_PRICE = new HashMap<>() {{
        put("4", .30);
        put("8", 0.60);
        put("12", 0.90);
    }};
    public static final Set<String> OPTIONS = new HashSet<>() {{
        addAll(List.of("American", "Provolone", "Cheddar", "Swiss"));
    }};

    public CheeseTopping(String name, boolean extra) {
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
