package com.pluralsight.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Customer customer;
    private List<Sandwich> sandwiches = new ArrayList<>();
    private Drink drink;
    private Chip chip;


    public void addSandwich(Sandwich sandwich){
        this.getSandwiches().add(sandwich);
    }
}
