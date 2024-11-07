package com.pluralsight.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Order {
    private Customer customer;
    private List<Sandwich> sandwiches;
    private Drink drink;
    private Chip chip;

}
