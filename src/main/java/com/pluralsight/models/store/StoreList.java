package com.pluralsight.models.store;

import java.util.List;

public abstract class StoreList {
    private static List<Store> storeList = List.of(
            LittleLucca.getInstance()
    );

    public static void main(String[] args) {
        printStoreList();
        System.out.println(storeList.getFirst().getName());
        System.out.println("sig = " + LittleLucca.getInstance().getSignatureSandwich());
    }

    public static void printStoreList(){
        for (Store store : storeList){
            System.out.println(store.getName());
        }
    }
}
