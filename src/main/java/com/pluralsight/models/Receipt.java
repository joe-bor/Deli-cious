package com.pluralsight.models;

import java.time.LocalDateTime;

public record Receipt(LocalDateTime transactionDate, Order order) {

    public Receipt(Order order) {
        this(LocalDateTime.now(), order);
    }

}
