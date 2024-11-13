package com.pluralsight.models;

import com.pluralsight.models.enums.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Drink implements SizeDependentPricing {

    private String flavor;
    private Size size;

    @Override
    public String toString(){
        return String.format("%s %s", this.size, this.flavor);
    }

    @Override
    public double getPriceBasedOnSize(Size size) {
        return switch (this.size){
            case SMALL -> 2.00;
            case MEDIUM -> 2.50;
            case LARGE -> 3.00;
        };
    }
}
