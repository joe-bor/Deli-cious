package com.pluralsight.models.topping;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegularTopping extends Topping {
    public static final Set<String> OPTIONS = new HashSet<>() {{
        addAll(List.of("Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapenos", "Cucumbers", "Pickles", "Guacamole", "Mushrooms"));
    }};
    public static final Set<String> SAUCES = new HashSet<>() {{
        addAll(List.of("Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette"));
    }};
    public static final Set<String> SIDES = new HashSet<>() {{
        addAll(List.of("Au Jus", "Sauce"));
    }};

    public RegularTopping(String name){
        super(name);
    }
}
