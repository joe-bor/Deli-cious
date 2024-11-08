package com.pluralsight.models;

import lombok.Getter;

@Getter
public abstract class Topping {
    private String name;

    Topping(String name) {
        this.name = name;
    }

    Topping() {
    }
}
