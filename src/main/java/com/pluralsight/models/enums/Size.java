package com.pluralsight.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Size {
    SMALL("4 in", "8 oz"),
    MEDIUM("8 in", "12 oz"),
    LARGE("12 in", "16 oz");

    private final String inOfSandwich;
    private  final String ozOfDrink;
}
