package com.pluralsight.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class PremiumTopping extends Topping implements SizeDependentPricing {
    private boolean extra;

    public PremiumTopping(String name, boolean extra) {
        super(name);
        this.extra = extra;
    }
}
