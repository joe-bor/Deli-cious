package com.pluralsight.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class PremiumTopping extends Topping {
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
    public static final Set<String> MEAT_OPTIONS =  new HashSet(){{
        addAll(List.of("Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"));
    }};
    public static final Set<String> CHEESE_OPTIONS = new HashSet(){{
        addAll(List.of("American", "Provolone", "Cheddar", "Swiss"));
    }};

    private boolean extra;
    private String type; // Meat || Cheese

    public PremiumTopping(String name, boolean extra, String type) {
        super(name);
        this.extra = extra;
        this.type = type;
    }

    public double getPrice(String breadSize) {
        double total = 0;
        total += SIZE_TO_PRICE.getOrDefault(breadSize, 0.0);
        total += SIZE_TO_EXTRA_PRICE.getOrDefault(breadSize, 0.0);

        return total;
    }

    @Override
    public String toString() {
        return "PremiumTopping{" +
                "name=" + super.getName() +
                " extra=" + extra +
                ", type='" + type + '\'' +
                '}';
    }
}
