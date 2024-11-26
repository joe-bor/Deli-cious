package com.pluralsight.data_manager;

import lombok.RequiredArgsConstructor;
import org.apache.commons.dbcp2.BasicDataSource;

@RequiredArgsConstructor
public class SandwichDAO {
    private final BasicDataSource BASIC_DATA_SOURCE;

    public void getAllSandwiches(){
        System.out.println("Getting all sandwiches");

    }
}
