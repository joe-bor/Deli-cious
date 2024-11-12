package com.pluralsight.models.sandwich_shop;

import java.util.List;

public abstract class SandwichShopList {
    private static List<SandwichShop> sandwichShops = List.of(
            LittleLucca.getInstance()
    );

    public static void main(String[] args) {
        printStoreList();
        System.out.println(sandwichShops.getFirst().getName());
        System.out.println("sig = " + LittleLucca.getInstance().getSignatureSandwich());
    }

    public static void printStoreList(){
        for (SandwichShop store : sandwichShops){
            System.out.println(store.getName());
        }
    }
}
