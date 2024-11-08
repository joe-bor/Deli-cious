package com.pluralsight.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Customer customer;
    private List<Sandwich> sandwiches = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private List<Chip> chips = new ArrayList<>();


    public void addSandwich(Sandwich sandwich){
        this.getSandwiches().add(sandwich);
    }

    public void addDrink(Drink drink){
        this.getDrinks().add(drink);
    }

    public void addChip(Chip chip){
        this.getChips().add(chip);
    }


    @Override
    public String toString() {
        return String.format("""
                ** ORDER **
                Customer: %-10s 
                Sandwich:
                %-30s
                Drinks: %-10s
                Chips: %-10ss
                """, this.getCustomer(), this.getSandwiches(), this.getDrinks(), this.getChips());
    }
}
