package com.techelevator;

import java.math.BigDecimal;

abstract public class Item {
    protected String name;
    protected BigDecimal price;
    protected String consumedMessage;


    public Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }


    abstract public String getConsumedMessage();


    public String getName() {
        return name;
    }


    public BigDecimal getPrice() {
        return price;
    }


}
