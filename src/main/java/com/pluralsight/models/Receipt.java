package com.pluralsight.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Receipt {
    private LocalDateTime localDateTime;
    private Order order;
    private String path;
}
