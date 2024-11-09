package com.pluralsight.models;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SandwichSize {
    SMALL("4", 5.50),
    MEDIUM("8", 7.0),
    LARGE("12", 8.50);

    private final String size;
    private final double basePrice;
}
