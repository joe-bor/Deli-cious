package com.pluralsight.models.topping;

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
