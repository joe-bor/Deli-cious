package com.pluralsight.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Chip {
    private String name;
    private double cost;

    public Chip(String name){
        this.name = name;
        this.cost = 1.50;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
