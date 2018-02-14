package com.techelevator;

import java.math.BigDecimal;

public class Chips extends Item {
    public Chips(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public String toString() {
        return name + " " + price;

    }

    @Override
    public String getConsumedMessage() {
        return "Crunch Crunch, Yum!";
    }
}

