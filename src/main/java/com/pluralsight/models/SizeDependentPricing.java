package com.pluralsight.models;

import com.pluralsight.models.enums.Size;

@FunctionalInterface
public interface SizeDependentPricing {
    double getPriceBasedOnSize(Size size);
}
