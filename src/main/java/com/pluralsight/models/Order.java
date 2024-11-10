package com.pluralsight.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Customer customer;
    private List<Sandwich> sandwiches = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private List<Chip> chips = new ArrayList<>();


    public void addSandwich(Sandwich sandwich) {
        this.getSandwiches().addFirst(sandwich);
    }

    public void addDrink(Drink drink) {
        this.getDrinks().add(drink);
    }

    public void addChip(Chip chip) {
        this.getChips().add(chip);
    }

    public double getTotalCost() {
        return getSandwichCost() + getDrinkCost() + getChipsCost();
    }

    public double getSandwichCost() {
        return this.getSandwiches().stream()
                .mapToDouble(Sandwich::getTotalCost)
                .sum();
    }

    public double getDrinkCost() {
        return this.getDrinks().stream()
                .mapToDouble(drink -> drink.getPriceBasedOnSize(null))
                .sum();
    }

    public double getChipsCost() {
        return this.getChips().stream()
                .map(Chip::getCost)
                .reduce(0.0, Double::sum);
    }


    @Override
    public String toString() {
        String formattedSandwiches = sandwiches.stream()
                .map(Sandwich::toString)
                .collect(Collectors.joining("\n"));

        String formattedDrinks = drinks.stream()
                .map(Drink::toString)
                .collect(Collectors.joining(", "));

        String formattedChips = chips.stream()
                .map(Chip::toString)
                .collect(Collectors.joining(", "));

        return String.format("""
                        ** ORDER **
                        Customer: %-10s
                        Sandwich:
                        %-30s
                        Drinks: %-10s
                        Chips: %-10s
                        -----
                        Total: %.2f
                        
                        """,
                this.getCustomer(), formattedSandwiches, formattedDrinks, formattedChips, this.getTotalCost());
    }

}
