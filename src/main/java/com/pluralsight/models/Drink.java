package com.pluralsight.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Drink {
    private String name;
    private String size;
    private double cost;
}
