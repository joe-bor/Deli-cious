package com.pluralsight.models.topping;

import com.pluralsight.models.SizeDependentPricing;
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
