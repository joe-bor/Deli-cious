package com.pluralsight.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Sandwich {
    private String breadType;
    private String breadSize;
    private List<Topping> toppings;
    private boolean toasted;

}
