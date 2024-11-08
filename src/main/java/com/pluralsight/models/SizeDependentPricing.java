package com.pluralsight.models;

@FunctionalInterface
public interface SizeDependentPricing {
    double getPriceBasedOn(String size);
}
