package com.pluralsight.models.sandwich_shop;

import com.pluralsight.models.Receipt;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SandwichShop {
    private String name;
    private String address;
    private List<Receipt> transactions;

    public SandwichShop(String name, String address) {
        this.name = name;
        this.address = address;
        this.transactions = new ArrayList<>();
    }
}
