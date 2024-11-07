package com.pluralsight.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Sandwich {
    private String breadType;
    private String sandwichSize;
    private List<Topping> toppings;
    private boolean toasted;

}
