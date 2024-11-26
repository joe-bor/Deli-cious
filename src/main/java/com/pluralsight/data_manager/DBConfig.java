package com.pluralsight.data_manager;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public abstract class DBConfig {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = new FileInputStream("src/main/resources/db.properties")) {
            PROPERTIES.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Error loading configuration", e);
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
