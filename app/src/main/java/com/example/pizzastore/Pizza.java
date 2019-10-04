package com.example.pizzastore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pizza implements Serializable {
     List<String> toppings = new ArrayList<>();
    boolean check;

    public Pizza(List toppings, boolean check) {
        this.toppings = toppings;
        this.check = check;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "toppings=" + toppings +
                ", check=" + check +
                '}';
    }
}
