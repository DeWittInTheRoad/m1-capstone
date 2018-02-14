package com.techelevator;

import java.math.BigDecimal;

public class Beverage extends Item{
    public Beverage(String name, BigDecimal price) {
        super(name, price);

    }
    @Override
    public String toString() {
        return name + " " + price;

    }

    @Override
    public String getConsumedMessage() {
        return "Glug Glug, Yum!";
    }
}
