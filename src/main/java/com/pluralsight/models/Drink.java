package com.pluralsight.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class Drink implements SizeDependentPricing {
    private static final Map<String, Double> SIZE_TO_PRICE = new HashMap<>(){{
        put("Small", 2.00);
        put("Medium", 2.50);
        put("Large", 3.00);
    }};

    private String flavor;
    private String size;
//    private double cost;


    @Override
    public double getPriceBasedOn(String size) {
        return SIZE_TO_PRICE.getOrDefault(size, 3.00);
    }
}
