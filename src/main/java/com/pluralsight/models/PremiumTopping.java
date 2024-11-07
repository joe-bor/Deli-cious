package com.pluralsight.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public  class PremiumTopping extends Topping{
    private boolean extra;
    private double cost;
    private String type; // Meat || Cheese
}
