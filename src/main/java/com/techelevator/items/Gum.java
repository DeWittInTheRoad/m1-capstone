package com.techelevator.items;

import java.math.BigDecimal;

public class Gum extends Item {
    public Gum(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public String toString() {
        return name + " " + price;

    }

    @Override
    public String getConsumedMessage() {
        return "Chew Chew, Yum!";
    }
}
