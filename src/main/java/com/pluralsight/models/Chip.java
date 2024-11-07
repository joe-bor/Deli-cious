package com.pluralsight.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Chip {
    private String name;
    private double cost;
}
