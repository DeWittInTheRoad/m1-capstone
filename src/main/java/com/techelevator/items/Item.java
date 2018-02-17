package com.techelevator.items;

import java.math.BigDecimal;

public abstract class Item {
    protected String name;
    protected BigDecimal price;
    protected String consumedMessage;


    public Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }


    public abstract String getConsumedMessage();


    public String getName() {
        return name;
    }


    public BigDecimal getPrice() {
        return price;
    }


}
