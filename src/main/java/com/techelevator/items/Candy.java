package com.techelevator.items;

import java.math.BigDecimal;

public class Candy extends Item {
    public Candy(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public String toString() {
        return name + " " + price;

    }

    @Override
    public String getConsumedMessage() {
        return "Munch Munch, Yum!";
    }
}
