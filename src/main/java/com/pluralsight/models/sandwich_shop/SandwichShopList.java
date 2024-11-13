package com.pluralsight.models.sandwich_shop;

import lombok.Getter;

import java.util.List;

public abstract class SandwichShopList {
    @Getter
    private static final List<SandwichShop> sandwichShops = List.of(
            LittleLucca.getInstance(),
            new BonusShop()
    );

    public static void printStoreList(){
        for (int i = 0; i < sandwichShops.size(); i++) {
            SandwichShop sandwichShop =  sandwichShops.get(i);
            System.out.println(String.format("  [%d] - %s", i + 1, sandwichShop.getName()));
        }
    }
}
