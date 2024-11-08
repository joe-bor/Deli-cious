package com.pluralsight.models;

import lombok.Data;

import java.util.List;

@Data
public class Store {
    private String name;
    private String address;
    private List<Receipt> transactions;

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
