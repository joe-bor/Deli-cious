package com.pluralsight.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegularTopping extends Topping{
    private double cost = 0;

}
