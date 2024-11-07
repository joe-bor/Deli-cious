package com.pluralsight.models;

import lombok.Getter;

@Getter
public abstract class Topping {
    private final String name;

    Topping(String name){
        this.name = name;
    }

    Topping(){
        this.name = "Default topping name";
    }
}
