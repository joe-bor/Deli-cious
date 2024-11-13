package com.pluralsight.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private String name;

    @Override
    public String toString() {
        return this.getName();
    }
}
